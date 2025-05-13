package com.monew.global.exception.user;

import com.monew.global.exception.ErrorCode;
import com.monew.global.exception.MonewException;

public class UserException extends MonewException {

  public UserException(ErrorCode errorCode) {
    super(errorCode);
  }

  public UserException(ErrorCode errorCode, Throwable cause) {
    super(errorCode, cause);
  }
}
