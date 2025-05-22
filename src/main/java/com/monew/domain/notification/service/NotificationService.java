package com.monew.domain.notification.service;

import com.monew.domain.notification.dto.CursorPageResponseNotificationDto;
import com.monew.domain.notification.entity.Notification;
import java.util.List;
import java.util.UUID;

public interface NotificationService {

  // 구동 중인 관심사와 관련된 기사가 새로 등록된 경우 생성
  //Notification createWithInterest(List<Article> articles);

  // 내가 작성한 댓글에 좋아요가 눌릴 경우 생성
  Notification createWithComment(UUID userId, UUID likeUserID);

  // 모든 알림 확인
  void updateAllByUserId(UUID userId);

  // 특정 알림 확인
  void updateByUserId(UUID userId);

  // 삭제 - 일주일이 경과된 알림은 자동 삭제, 매일 배치로 수행 - 수정 필요
  void delete();

  // 조회 - 확인하지 않은 알림을 조회 - 커서 페이지네이션으로 구현 - 수정 필요
  CursorPageResponseNotificationDto find();
}
