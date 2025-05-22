package com.monew.global.util.mapper.interest;

import com.monew.domain.interest.dto.InterestDto;
import com.monew.domain.interest.entity.Interest;
import com.monew.domain.interest.entity.Keyword;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InterestMapper {

  default String map(Keyword keyword) {
    return keyword.getName();
  }

  default List<String> map(List<Keyword> keywords) {
    return keywords.stream()
        .map(this::map)
        .collect(Collectors.toList());
  }

  InterestDto toDto(Interest interest, boolean subscribedByMe);
}
