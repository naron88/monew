package team03.monew.module.interest.exception;

import team03.monew.module.common.exception.ErrorCode;
import team03.monew.module.common.exception.MonewException;

public class InterestException extends MonewException {

  public InterestException(ErrorCode errorCode) {
    super(errorCode);
  }

  public InterestException(ErrorCode errorCode, Throwable cause) {
    super(errorCode, cause);
  }
}
