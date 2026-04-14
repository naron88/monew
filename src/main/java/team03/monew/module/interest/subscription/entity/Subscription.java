package team03.monew.module.interest.subscription.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team03.monew.module.common.entity.BaseEntity;
import team03.monew.module.user.entity.User;
import team03.monew.module.interest.entity.Interest;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
    name = "subscriptions",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "interest_id"})
    }
)
@Getter
@AllArgsConstructor
public class Subscription extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "interest_id", nullable = false)
  private Interest interest;
}
