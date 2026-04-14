package team03.monew.module.notification.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import team03.monew.module.notification.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, UUID>,
    CustomNotificationRepository {

}
