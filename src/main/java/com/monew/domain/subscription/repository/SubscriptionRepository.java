package com.monew.domain.subscription.repository;

import com.monew.domain.subscription.entity.Subscription;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

  Optional<Subscription> findByInterestIdAndUserId(UUID interestId, UUID userId);
}
