package com.monew.domain.comment.dto;

import java.util.UUID;

public record CommentRegisterRequest(
    UUID articleId,
    UUID userId,
    String content
) {

}
