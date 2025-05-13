package com.monew.global.exception.user.custom;

import com.monew.global.exception.ErrorCode;
import com.monew.global.exception.user.UserException;
import java.util.UUID;

public class UserNotFoundException extends UserException {

  public UserNotFoundException() {
    super(ErrorCode.USER_NOT_FOUND);
  }

  public static UserNotFoundException withId(UUID userId) {
    UserNotFoundException exception = new UserNotFoundException();
    exception.addDetail("userId", userId);
    return exception;
  }

  public static UserNotFoundException withEmail(String email) {
    UserNotFoundException exception = new UserNotFoundException();
    exception.addDetail("email", email);
    return exception;
  }

  public static UserNotFoundException isDeleted() {
    return new UserNotFoundException();
  }
}
