package com.monew.global.exception.user.custom;

import com.monew.global.exception.ErrorCode;
import com.monew.global.exception.user.UserException;

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
