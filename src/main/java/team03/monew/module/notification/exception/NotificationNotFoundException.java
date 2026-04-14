package team03.monew.module.notification.exception;

import java.util.UUID;
import team03.monew.module.common.exception.ErrorCode;

public class NotificationNotFoundException extends NotificationException {

  public NotificationNotFoundException() {
    super(ErrorCode.NOTIFICATION_NOT_FOUND);
  }

  public static NotificationException withId(UUID id) {
    NotificationException exception = new NotificationNotFoundException();
    exception.addDetail("notificationId", id);
    return exception;
  }
}
