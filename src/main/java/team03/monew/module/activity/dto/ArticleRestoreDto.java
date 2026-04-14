package team03.monew.module.activity.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record ArticleRestoreDto(
    Instant restoreDate,
    List<UUID> restoredArticleIds,
    Long restoredArticleCount
) {

}
