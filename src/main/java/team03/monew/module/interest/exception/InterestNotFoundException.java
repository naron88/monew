package team03.monew.module.interest.exception;

import java.util.UUID;
import team03.monew.module.common.exception.ErrorCode;

public class InterestNotFoundException extends InterestException {

  public InterestNotFoundException() {
    super(ErrorCode.INTEREST_NOT_FOUND);
  }

  public static InterestNotFoundException withInterestId(UUID interestId) {
    InterestNotFoundException exception = new InterestNotFoundException();
    exception.addDetail("interestId", interestId);
    return exception;
  }
}
