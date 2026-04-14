package team03.monew.module.comments.dto;

import java.util.List;

public record InterestDto(
  String id,
  String name,
  List<String> keywords,
  long subscriberCount,
  boolean subscribedByMe
) {

}
