package team03.monew.module.interest.subscription.exception;

import java.util.UUID;
import team03.monew.module.common.exception.ErrorCode;

public class SubscriptionNotFoundException extends SubscriptionException {

  public SubscriptionNotFoundException() {
    super(ErrorCode.SUBSCRIPTION_NOT_FOUND);
  }

  public static SubscriptionNotFoundException withUserIdAndInterestId(UUID userId, UUID interestId) {
    SubscriptionNotFoundException exception = new SubscriptionNotFoundException();
    exception.addDetail("userId", userId);
    exception.addDetail("interestId", interestId);
    return exception;
  }
}
