package team03.monew.module.article.exception;

import team03.monew.module.common.exception.ErrorCode;
import team03.monew.module.common.exception.MonewException;

public class ArticleException extends MonewException {

    public ArticleException(ErrorCode errorCode) {
        super(errorCode);
    }
}
