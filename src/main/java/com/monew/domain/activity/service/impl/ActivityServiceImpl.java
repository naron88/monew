package com.monew.domain.activity.service.impl;

import com.monew.domain.activity.dto.CommentActivityDto;
import com.monew.domain.activity.dto.CommentLikeActivityDto;
import com.monew.domain.activity.dto.UserActivityDto;
import com.monew.domain.activity.entity.Activity;
import com.monew.domain.activity.repository.ActivityRepository;
import com.monew.domain.activity.service.ActivityService;
import com.monew.domain.articleView.dto.ArticleViewDto;
import com.monew.domain.subscription.dto.SubscriptionDto;
import com.monew.domain.user.entity.User;
import com.monew.global.exception.activity.custom.ActivityAlreadyExistsException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ActivityServiceImpl implements ActivityService {

  private final ActivityRepository activityRepository;

  @Override
  public Activity create(User user) {
    log.debug("활동 내역 생성 시작: userId={}", user.getId());

    UUID userId = user.getId();
    if (activityRepository.existsByUserId(userId)) {
      throw ActivityAlreadyExistsException.withUserId(userId);
    }

    Activity activity = new Activity(user);

    activityRepository.save(activity);
    log.info("활동 내역 생성 성공: activityId={}", activity.getId());
    return activity;
  }

  @Override
  public UserActivityDto findByUserId(UUID userId) {
    Activity activity = activityRepository.findByUserId(userId)
        .orElseThrow(() -> ActivityAlreadyExistsException.withUserId(userId));

    return null;
  }

  // 구독 중인 관심사 찾기
  private List<SubscriptionDto> findSubscription() {
    return List.of();
  }

  // 최근 작성한 댓글 찾기
  private List<CommentActivityDto> findRecentComments() {
    return List.of();
  }

  // 최근 좋아요 누른 댓글 찾기
  private List<CommentLikeActivityDto> findRecentLikedComments() {
    return List.of();
  }

  // 최근 본 뉴스 찾기
  private List<ArticleViewDto> findRecentViewedNews() {
    return List.of();
  }
}
