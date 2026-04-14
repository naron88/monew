package team03.monew.module.interest.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import team03.monew.module.comments.dto.InterestDto;
import team03.monew.module.comments.dto.SubscriptionDto;
import team03.monew.module.interest.entity.Keyword;
import team03.monew.module.interest.subscription.entity.Subscription;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

  @Mapping(target = "id", expression = "java(subscription.getId().toString())")
  @Mapping(target = "interestId", source = "interestDto.id")
  @Mapping(target = "interestName", source = "interestDto.name")
  @Mapping(target = "interestKeywords", source = "interestDto.keywords")
  @Mapping(target = "interestSubscriberCount", source = "interestDto.subscriberCount")
  @Mapping(target = "createdAt", source = "subscription.createdAt")
  SubscriptionDto toDto(Subscription subscription, InterestDto interestDto);

  @Mapping(target = "id", expression = "java(subscription.getId().toString())")
  @Mapping(target = "interestId", expression = "java(subscription.getInterest().getId().toString())")
  @Mapping(target = "interestName", source = "subscription.interest.name")
  @Mapping(target = "interestKeywords", source = "subscription.interest.keywords")
  @Mapping(target = "interestSubscriberCount", source = "subscription.interest.subscriberCount")
  @Mapping(target = "createdAt", source = "subscription.createdAt")
  SubscriptionDto toDto(Subscription subscription);

  default List<String> map(List<Keyword> keywords) {
    if (keywords == null) {
      return null;
    }
    return keywords.stream()
        .map(Keyword::getName)
        .toList();
  }
}
