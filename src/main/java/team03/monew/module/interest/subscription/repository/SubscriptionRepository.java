package team03.monew.module.interest.subscription.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import team03.monew.module.interest.entity.Interest;
import team03.monew.module.interest.subscription.entity.Subscription;
import team03.monew.module.user.entity.User;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID>,
    CustomSubscriptionRepository {

  boolean existsByUser_IdAndInterest_Id(UUID userId, UUID interestId);

  Optional<Subscription> findByUser_IdAndInterest_Id(UUID userId, UUID interestId);

  List<Subscription> findAllByInterest(Interest interest);

  List<Subscription> findAllByUser(User user);
}
