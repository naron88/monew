package team03.monew.module.interest.subscription.exception;

import team03.monew.module.common.exception.ErrorCode;
import team03.monew.module.common.exception.MonewException;

public class SubscriptionException extends MonewException {

  public SubscriptionException(ErrorCode errorCode) {
    super(errorCode);
  }

  public SubscriptionException(ErrorCode errorCode, Throwable cause) {
    super(errorCode, cause);
  }
}
