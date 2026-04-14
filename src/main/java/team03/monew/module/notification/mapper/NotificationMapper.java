package team03.monew.module.notification.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import team03.monew.module.notification.dto.NotificationDto;
import team03.monew.module.notification.entity.Notification;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

  @Mapping(source = "user.id", target = "userId")
  NotificationDto toDto(Notification notification);
}