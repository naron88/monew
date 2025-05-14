package com.monew.domain.comment.dto;

import java.time.Instant;
import java.util.List;

public record CursorPageResponseCommentDto(
    List<CommentDto> content,
    String nextCursor,
    Instant nextAfter,
    int size,
    int totalElements,
    boolean hasNext
) {

}
