package team03.monew.module.interest.exception;

import team03.monew.module.common.exception.ErrorCode;

public class EmptyKeywordListException extends InterestException {

  public EmptyKeywordListException() {
    super(ErrorCode.INVALID_KEYWORD_COUNT);
  }
}
