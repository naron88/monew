package team03.monew.module.interest.subscription.exception;

import java.util.UUID;
import team03.monew.module.common.exception.ErrorCode;

public class SubscriptionAlreadyExistException extends SubscriptionException {

  public SubscriptionAlreadyExistException() {
    super(ErrorCode.DUPLICATE_SUBSCRIPTION);
  }

  public static SubscriptionAlreadyExistException withInterestIdAndUserId(UUID userId,
      UUID interestId) {
    SubscriptionAlreadyExistException exception = new SubscriptionAlreadyExistException();
    exception.addDetail("userId", userId);
    exception.addDetail("interestId", interestId);
    return exception;
  }
}
