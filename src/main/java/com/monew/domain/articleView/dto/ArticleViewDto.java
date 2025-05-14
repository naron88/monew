package com.monew.domain.articleView.dto;

import java.time.Instant;
import java.util.UUID;

public record ArticleViewDto(
    UUID id,
    UUID viewedBy, // 기사를 조회한 사용자
    Instant createdAt,
    UUID articleId,
    String source, // 출처 ex) "NAVER"
    String sourceUrl,
    String articleTitle,
    Instant articlePublishedDate,
    String articleSummary,
    int articleCommentCount,
    int articleViewCount
) {

}
