package com.monew.global.exception.activity.custom;

import com.monew.global.exception.ErrorCode;
import com.monew.global.exception.activity.ActivityException;
import java.util.UUID;

public class ActivityAlreadyExistsException extends ActivityException {

  public ActivityAlreadyExistsException() {
    super(ErrorCode.DUPLICATE_ACTIVITY);
  }

  public static ActivityAlreadyExistsException withUserId(UUID userId) {
    ActivityAlreadyExistsException exception = new ActivityAlreadyExistsException();
    exception.addDetail("userId", userId);
    return exception;
  }

}
