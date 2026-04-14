package team03.monew.module.user.exception;

import team03.monew.module.common.exception.ErrorCode;

public class InvalidException extends UserException {

  public InvalidException() {
    super(ErrorCode.INVALID_USER_CREDENTIALS);
  }

  public static InvalidException wrongPassword() {
    InvalidException exception = new InvalidException();
    return exception;
  }
}
