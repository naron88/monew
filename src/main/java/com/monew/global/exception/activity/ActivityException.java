package com.monew.global.exception.activity;

import com.monew.global.exception.ErrorCode;
import com.monew.global.exception.MonewException;

public class ActivityException extends MonewException {

  public ActivityException(ErrorCode errorCode) {
    super(errorCode);
  }

  public ActivityException(ErrorCode errorCode, Throwable cause) {
    super(errorCode, cause);
  }
}
