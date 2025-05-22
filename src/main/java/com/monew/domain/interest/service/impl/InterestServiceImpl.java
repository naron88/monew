package com.monew.domain.interest.service.impl;

import com.monew.domain.interest.dto.CursorPageResponseInterestDto;
import com.monew.domain.interest.dto.InterestDto;
import com.monew.domain.interest.dto.InterestFindRequest;
import com.monew.domain.interest.dto.InterestRegisterRequest;
import com.monew.domain.interest.dto.InterestUpdateRequest;
import com.monew.domain.interest.entity.Interest;
import com.monew.domain.interest.entity.Keyword;
import com.monew.domain.interest.repository.InterestRepository;
import com.monew.domain.interest.service.InterestService;
import com.monew.domain.subscription.dto.SubscriptionDto;
import com.monew.domain.subscription.entity.Subscription;
import com.monew.domain.subscription.repository.SubscriptionRepository;
import com.monew.domain.user.entity.User;
import com.monew.domain.user.repository.UserRepository;
import com.monew.global.exception.interest.custom.InterestAlreadyExistsException;
import com.monew.global.exception.interest.custom.InterestNotFoundException;
import com.monew.global.exception.user.custom.UserNotFoundException;
import com.monew.global.util.mapper.interest.InterestMapper;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class InterestServiceImpl implements InterestService {

  private final InterestRepository interestRepository;
  private final UserRepository userRepository;
  private final InterestMapper interestMapper;
  private final SubscriptionRepository subscriptionRepository;

  @Override
  public InterestDto create(InterestRegisterRequest request) {
    log.debug("관심사 생성 시작, name={}", request.name());

    // 이름 유사 검사
    for (Interest interest : interestRepository.findAll()) {
      if (isSimilar(request.name(), interest.getName())) {
        throw InterestAlreadyExistsException.withName(request.name());
      }
    }
    Interest interest = new Interest(request.name());

    // 키워드 생성
    createKeyword(request.keywords(), interest);

    interestRepository.save(interest);

    log.info("관심사 생성 완료, name={}", interest.getName());
    return interestMapper.toDto(interest, false);
  }

  @Override
  public InterestDto update(UUID interestId, InterestUpdateRequest request) {
    log.debug("관심사 수정 시작, interestId={}", interestId);
    Interest interest = interestRepository.findById(interestId)
        .orElseThrow(() -> InterestNotFoundException.withId(interestId));

    createKeyword(request.keywords(), interest);

    log.info("관심사 수정 완료, interestId={}", interestId);
    return interestMapper.toDto(interest, false);
  }

  @Override
  public void delete(UUID interestId) {
    log.debug("관심사 삭제 시작, interestId={}", interestId);
    if (interestRepository.existsById(interestId)) {
      throw InterestNotFoundException.withId(interestId);
    }

    interestRepository.deleteById(interestId);
    log.info("관심사 삭제 완료");
  }

  @Override
  public CursorPageResponseInterestDto findByCursor(InterestFindRequest request, UUID userId) {
    log.info("조회 서비스 시작");
    List<Interest> interests = interestRepository.findByCursor(request);

    boolean hasNext = interests.size() > request.limit();
    int size = Math.min(interests.size(), request.limit());
    if (interests.isEmpty()) {
      return new CursorPageResponseInterestDto(List.of(), null, null, 0, 0, false);
    }
    Interest last = interests.get(size - 1);
    // 커서 response
    // 마지막 interest의 name or 구독자수
    String nextCursor = null;
//    String nextCursor = request.orderBy().equals("name")
//        ? last.getName()
//        : String.valueOf(last.getSubscriberCount());

    // 마지막 interest.createdAt
    Instant nextAfter = null;
//    Instant nextAfter = last.getCreatedAt();

    int totalElements = interestRepository.countAll();


    List<InterestDto> interestDtos = interests.stream()
        .limit(request.limit())
        .map(interest -> interestMapper.toDto(interest, false))
        .toList();

    log.info("조회 서비스 종료");
    return new CursorPageResponseInterestDto(interestDtos,
        nextCursor, nextAfter, size, totalElements, hasNext);
  }

  @Override
  public SubscriptionDto createSubs(UUID interestId, UUID userId) {
    Interest interest = interestRepository.findById(interestId)
        .orElseThrow(() -> InterestNotFoundException.withId(interestId));

    User user = userRepository.findById(userId)
        .orElseThrow(() -> UserNotFoundException.withId(userId));
    Subscription subscription = new Subscription(user, interest);
    interest.subscribe();

    subscriptionRepository.save(subscription);
    SubscriptionDto subscriptionDto = new SubscriptionDto(subscription.getId(), interestId, interest.getName(), interest.getKeywords(), interest.getSubscriberCount(), subscription.getCreatedAt());
    return subscriptionDto;
  }

  @Override
  public void deleteSubs(UUID interestId, UUID userId) {
    Interest interest = interestRepository.findById(interestId)
            .orElseThrow(() -> InterestNotFoundException.withId(interestId));

    // 바꿀 예정
    subscriptionRepository.findByInterestIdAndUserId(interestId, userId)
        .orElseThrow(() -> new IllegalArgumentException());

    interest.cancelSubscribe();
  }

  private boolean isSimilar(String name1, String name2) {
    // 두 문자열을 같게 만드는 최소 편집 횟수를 구하는 객체
    LevenshteinDistance distance = new LevenshteinDistance();

    // 두 문자열의 차이 계산
    int dist = distance.apply(name1, name2);
    int maxLength = Math.max(name1.length(), name2.length());

    // 유사도 = 1 - 차이 / 문자열 최대 길이(거리 비율)
    double similarity = 1.0 - (double) dist / maxLength;

    // 유사도가 0.8 이상인지 확인
    return similarity >= 0.8;
  }


  private void createKeyword(List<String> keywordList, Interest interest) {
    // 키워드 생성

    List<Keyword> keywords = keywordList.stream()
        .map(keyword -> new Keyword(keyword, interest))
        .toList();

    interest.update(keywords); // 키워드 새로 생성
  }
}
