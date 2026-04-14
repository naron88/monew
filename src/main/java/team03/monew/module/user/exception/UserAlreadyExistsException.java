package team03.monew.module.user.exception;

import team03.monew.module.common.exception.ErrorCode;

public class UserAlreadyExistsException extends UserException {
  public UserAlreadyExistsException() {
    super(ErrorCode.DUPLICATE_USER);
  }

  public static UserAlreadyExistsException withEmail(String email) {
    UserAlreadyExistsException exception = new UserAlreadyExistsException();
    exception.addDetail("email", email);
    return exception;
  }
}
