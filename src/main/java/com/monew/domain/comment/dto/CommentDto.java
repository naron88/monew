package com.monew.domain.comment.dto;

import java.time.Instant;
import java.util.UUID;

public record CommentDto(
    UUID id,
    UUID articleId,
    UUID userId,
    String userNickname,
    String content,
    int likeCount,
    boolean likedByMe,
    Instant createdAt
) {

}
