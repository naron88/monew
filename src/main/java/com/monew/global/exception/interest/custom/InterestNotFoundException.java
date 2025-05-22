package com.monew.global.exception.interest.custom;

import com.monew.global.exception.ErrorCode;
import com.monew.global.exception.interest.InterestException;
import java.util.UUID;

public class InterestNotFoundException extends InterestException {

  public InterestNotFoundException() {
    super(ErrorCode.INTEREST_NOT_FOUND);
  }

  public static InterestNotFoundException withId(UUID interestId) {
    InterestNotFoundException exception = new InterestNotFoundException();
    exception.addDetail("interestId", interestId);
    return exception;
  }
}
