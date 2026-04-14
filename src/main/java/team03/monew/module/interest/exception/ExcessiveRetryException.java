package team03.monew.module.interest.exception;

import team03.monew.module.common.exception.ErrorCode;

public class ExcessiveRetryException extends InterestException{

  public ExcessiveRetryException() {
    super(ErrorCode.ERROR_MAX_RETRY_EXCEEDED);
  }
}
