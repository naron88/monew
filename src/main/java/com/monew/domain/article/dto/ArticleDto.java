package com.monew.domain.article.dto;

import java.time.Instant;
import java.util.UUID;

public record ArticleDto(
    UUID id,
    String source,
    String sourceUrl,
    String title,
    Instant publishDate,
    String summary,
    int commentCount,
    int viewCount,
    boolean viewedByMe
) {

}
