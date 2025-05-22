package com.monew.domain.interest.service;

import com.monew.domain.interest.dto.CursorPageResponseInterestDto;
import com.monew.domain.interest.dto.InterestDto;
import com.monew.domain.interest.dto.InterestFindRequest;
import com.monew.domain.interest.dto.InterestRegisterRequest;
import com.monew.domain.interest.dto.InterestUpdateRequest;
import com.monew.domain.subscription.dto.SubscriptionDto;
import java.time.Instant;
import java.util.UUID;

public interface InterestService {

  // 등록
  // 80% 이상 유사한 관심사는 등록 불가 - 1. 관심사의 이름이 80% 유사한게 안되는지, 2. 키워드의 유사도 인지 / 일단 1번으로 구현
  // 키워드는 여러 개를 가질 수 있으며, 뉴스 기사 검색에 활용됩니다
  InterestDto create(InterestRegisterRequest request);

  // 수정
  InterestDto update(UUID interestId, InterestUpdateRequest request);

  // 삭제
  void delete(UUID interestId);

  // 목록 조회
  CursorPageResponseInterestDto findByCursor(InterestFindRequest request, UUID userId);

  // 구독 하기
  SubscriptionDto createSubs(UUID interestId, UUID userId);

  // 구독 취소
  void deleteSubs(UUID interestId, UUID userId);
}
