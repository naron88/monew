package team03.monew.module.notification.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import team03.monew.module.common.dto.CursorPageResponse;
import team03.monew.module.notification.dto.NotificationDto;
import team03.monew.module.article.entity.Article;
import team03.monew.module.comments.entity.Comment;
import team03.monew.module.user.entity.User;

public interface NotificationService {
  List<NotificationDto> createInterestNotification(List<Article> articles);

  NotificationDto createCommentLikeNotification(Comment comment, User user);

  void readNotification(UUID id, UUID userId);

  void readAllNotification(UUID userId);

  CursorPageResponse<NotificationDto> findAll(UUID userId, String cursor, Instant after, Integer limit);

}
