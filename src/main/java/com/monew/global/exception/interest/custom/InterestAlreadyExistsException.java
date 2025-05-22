package com.monew.global.exception.interest.custom;

import com.monew.global.exception.ErrorCode;
import com.monew.global.exception.interest.InterestException;

public class InterestAlreadyExistsException extends InterestException {

  public InterestAlreadyExistsException() {
    super(ErrorCode.DUPLICATE_INTEREST);
  }

  public static InterestAlreadyExistsException withName(String name) {
    InterestAlreadyExistsException exception = new InterestAlreadyExistsException();
    exception.addDetail("name", name);
    return exception;
  }
}
