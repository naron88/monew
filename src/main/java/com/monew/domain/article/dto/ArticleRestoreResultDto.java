package com.monew.domain.article.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record ArticleRestoreResultDto(
    Instant restoreDate,
    List<UUID> restoredArticleIds,
    int restoreArticleCount
) {

}
