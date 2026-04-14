package team03.monew.module.user.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import team03.monew.module.activity.dto.ArticleViewDto;
import team03.monew.module.article.dto.CommentActivityDto;
import team03.monew.module.article.dto.CommentLikeActivityDto;
import team03.monew.module.comments.dto.SubscriptionDto;

public record ActivityDto(
    UUID id,
    String email,
    String nickname,
    Instant createdAt,
    List<SubscriptionDto> subscriptions,
    List<CommentActivityDto> comments,
    List<CommentLikeActivityDto> commentLikes,
    List<ArticleViewDto> articleViews) {

}
