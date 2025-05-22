package com.monew.domain.interest.dto;

import com.monew.domain.interest.entity.Keyword;
import java.util.List;
import java.util.UUID;

public record InterestDto(
    UUID id,
    String name,
    List<String> keywords,
    int subscriberCount, // 구독자 수
    boolean subscribedByMe // 요청자 구독 여부
) {

}
