package team03.monew.module.notification.exception;

import team03.monew.module.common.exception.ErrorCode;
import team03.monew.module.common.exception.MonewException;

public class NotificationException extends MonewException {

  public NotificationException(ErrorCode errorCode) {
    super(errorCode);
  }

  public NotificationException(ErrorCode errorCode, Throwable cause) {
    super(errorCode, cause);
  }

}
