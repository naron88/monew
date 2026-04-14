package team03.monew.module.interest.subscription.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import team03.monew.module.interest.entity.Interest;
import team03.monew.module.interest.service.InterestService;

@Component
@RequiredArgsConstructor
public class SubscriptionEventListener {

  private final InterestService interestService;

  @EventListener
  public void handleSubscriptionCreate(SubscriptionCreateEvent event) {
    Interest interest = event.getInterest();
    interestService.updateSubscriberCount(interest, true);
  }

  @EventListener
  public void handleSubscriptionDelete(SubscriptionDeleteEvent event) {
    Interest interest = event.getInterest();
    interestService.updateSubscriberCount(interest, false);
  }
}
