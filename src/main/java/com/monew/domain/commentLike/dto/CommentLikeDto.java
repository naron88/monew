package com.monew.domain.commentLike.dto;

import java.time.Instant;
import java.util.UUID;

public record CommentLikeDto(
    UUID id,
    UUID likedBy, // 졸아요한 사용자 id
    Instant createdAt,
    UUID commentId,
    UUID articleId,
    UUID commentUserId, // 작성자 id
    String commentUserNickname,
    String commentContent,
    int commentLikeCount,
    Instant commentCreatedAt
) {

}
