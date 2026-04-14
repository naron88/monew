package team03.monew.module.interest.exception;

import team03.monew.module.common.exception.ErrorCode;

public class InterestAlreadyExistException extends InterestException{

  public InterestAlreadyExistException() {
    super(ErrorCode.SIMILAR_INTEREST_EXISTS);
  }

  public static InterestAlreadyExistException withInterestName(String interestName, String existingInterestName) {
    InterestAlreadyExistException exception = new InterestAlreadyExistException();
    exception.addDetail("interestName", interestName);
    exception.addDetail("existingInterestName", existingInterestName);
    return exception;
  }
}
