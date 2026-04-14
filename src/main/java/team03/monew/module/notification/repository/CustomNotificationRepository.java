package team03.monew.module.notification.repository;

import java.time.Instant;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team03.monew.module.notification.entity.Notification;

public interface CustomNotificationRepository {

    void confirmAllByUserId(UUID userId);

    int deleteAllConfirmNotification(Instant time);

    Page<Notification> findPageWithCursor(UUID userId, String cursor, Pageable pageable);

}
