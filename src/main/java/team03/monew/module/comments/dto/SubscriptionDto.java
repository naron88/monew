package team03.monew.module.comments.dto;

import java.time.Instant;
import java.util.List;

public record SubscriptionDto(
    String id,
    String interestId,
    String interestName,
    List<String> interestKeywords,
    long interestSubscriberCount,
    Instant createdAt
) {

}
