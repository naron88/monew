package com.monew.global.exception.user.custom;

import com.monew.global.exception.ErrorCode;
import com.monew.global.exception.user.UserException;

public class ForbiddenException extends UserException {

  public ForbiddenException() {
    super(ErrorCode.FORBIDDEN_REQUEST);
  }

  public static ForbiddenException WrongUserId() {
    return new ForbiddenException();
  }
}
