package com.monew.domain.interest.dto;

import java.util.List;

public record InterestUpdateRequest(
    List<String> keywords
) {

}
