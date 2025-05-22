package com.monew.domain.notification.service.impl;

import com.monew.domain.notification.dto.CursorPageResponseNotificationDto;
import com.monew.domain.notification.entity.Notification;
import com.monew.domain.notification.entity.Notification.ResourceType;
import com.monew.domain.notification.repository.NotificationRepository;
import com.monew.domain.notification.service.NotificationService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class NotificationServiceImpl implements NotificationService {

  private final NotificationRepository notificationRepository;

//  @Override
//  public Notification createWithInterest(UUID userId, String interest, int count) {
//    String content = interest + "와 관련된 기사가 " + count + "건 등록되었습니다.";
//    Notification notification = new Notification(content, ResourceType.INTEREST, )
//    return null;
//  }

  @Override
  public Notification createWithComment(UUID userId, UUID likeUserID) {
    return null;
  }

  @Override
  public void updateAllByUserId(UUID userId) {

  }

  @Override
  public void updateByUserId(UUID userId) {

  }

  @Override
  public void delete() {

  }

  @Override
  public CursorPageResponseNotificationDto find() {
    return null;
  }
}
