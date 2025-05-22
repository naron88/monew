package com.monew.domain.subscription.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record SubscriptionDto(
    UUID id,
    UUID interestId,
    String interestName,
    List<com.monew.domain.interest.entity.Keyword> interestKeywords,
    int interestSubscriberCount,
    Instant createdAt
) {

}
