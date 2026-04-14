package team03.monew.module.interest.subscription.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import team03.monew.module.interest.entity.Interest;

@Getter
@RequiredArgsConstructor
public class SubscriptionDeleteEvent {

  private final Interest interest;
}
