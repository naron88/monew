package team03.monew.module.article.exception;

import java.util.UUID;
import team03.monew.module.common.exception.ErrorCode;

public class ArticleNotFoundException extends ArticleException {

    public ArticleNotFoundException() {
        super(ErrorCode.ARTICLE_NOT_FOUND);
    }

    public static ArticleNotFoundException withId(UUID articleId) {
        ArticleNotFoundException exception = new ArticleNotFoundException();
        exception.addDetail("articleId", articleId);
        return exception;
    }
}
