package team03.monew.module.user.exception;

import team03.monew.module.common.exception.ErrorCode;
import team03.monew.module.common.exception.MonewException;

public class UserException extends MonewException {

  public UserException(ErrorCode errorCode) {
    super(errorCode);
  }

  public UserException(ErrorCode errorCode, Throwable cause) {
    super(errorCode, cause);
  }
}
