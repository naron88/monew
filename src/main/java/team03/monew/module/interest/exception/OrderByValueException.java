package team03.monew.module.interest.exception;

import team03.monew.module.common.exception.ErrorCode;

public class OrderByValueException extends InterestException {

  public OrderByValueException() {
    super(ErrorCode.INVALID_ORDER_BY);
  }

  public static OrderByValueException withOrderBy(String orderBy) {
    OrderByValueException exception = new OrderByValueException();
    exception.addDetail("orderBy", orderBy);
    return exception;
  }
}
