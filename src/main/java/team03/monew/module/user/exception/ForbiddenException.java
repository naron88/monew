package team03.monew.module.user.exception;

import team03.monew.module.common.exception.ErrorCode;

public class ForbiddenException extends UserException {

  public ForbiddenException() {
    super(ErrorCode.FORBIDDEN_REQUEST);
  }

  public static ForbiddenException WrongUserId() {
    ForbiddenException exception = new ForbiddenException();
    return exception;
  }
}
