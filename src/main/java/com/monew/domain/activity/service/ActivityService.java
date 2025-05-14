package com.monew.domain.activity.service;

import com.monew.domain.activity.dto.CommentActivityDto;
import com.monew.domain.activity.dto.CommentLikeActivityDto;
import com.monew.domain.activity.dto.UserActivityDto;
import com.monew.domain.activity.entity.Activity;
import com.monew.domain.articleView.dto.ArticleViewDto;
import com.monew.domain.subscription.dto.SubscriptionDto;
import com.monew.domain.user.entity.User;
import java.util.List;
import java.util.UUID;

public interface ActivityService {

  // 활동 내역 생성
  Activity create(User user);

  // 사용자 활동 내역 조회
  UserActivityDto findByUserId(UUID userId);
}
