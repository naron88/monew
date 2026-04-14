package team03.monew.module.article.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import team03.monew.module.article.entity.Article;

public interface ArticleRepositoryCustom {

    List<Article> findAllByCursor(
        String keyword,
        UUID interestId,
        List<String> sourceIn,
        LocalDateTime publishDateFrom,
        LocalDateTime publishDateTo,
        String orderBy,
        String direction,
        String cursor,
        LocalDateTime after,
        int limit
    );

    long countAllByCondition(
        String keyword,
        UUID interestId,
        List<String> sourceIn,
        LocalDateTime publishDateFrom,
        LocalDateTime publishDateTo
    );
}
