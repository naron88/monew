package com.monew.global.exception.user.custom;

import com.monew.global.exception.ErrorCode;
import com.monew.global.exception.user.UserException;

public class InvalidException extends UserException {

  public InvalidException() {
    super(ErrorCode.INVALID_USER_CREDENTIALS);
  }

  public static InvalidException wrongPassword() {
    return new InvalidException();
  }
}
