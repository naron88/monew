package com.monew.global.exception.interest;

import com.monew.global.exception.ErrorCode;
import com.monew.global.exception.MonewException;

public class InterestException extends MonewException {

  public InterestException(ErrorCode errorCode) {
    super(errorCode);
  }

  public InterestException(ErrorCode errorCode, Throwable cause) {
    super(errorCode, cause);
  }
}
