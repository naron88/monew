package com.monew.domain.interest.dto;

import java.time.Instant;
import java.util.UUID;

public record InterestFindRequest(

    String keyword, // 관심사 이름 or 키워드
    String orderBy, // name, subscriberCount
    String direction, // ASC, DESC
    String cursor, // 커서 값
    Instant after, // 보조 커서 값 - createdAt 값
    int limit // 커서 페이지 크기
) {

}
