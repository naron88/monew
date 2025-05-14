package com.monew.domain.notification.dto;

import java.time.Instant;
import java.util.UUID;

public record NotificationDto(
    UUID id,
    Instant createdAt,
    Instant updatedAt,
    boolean confirmed,
    UUID userId,
    String content,
    String resourceType, // interest, comment
    UUID resourceId
) {

}
