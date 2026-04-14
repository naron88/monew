package team03.monew.module.interest.subscription.service;

import java.util.List;
import java.util.UUID;
import team03.monew.module.comments.dto.SubscriptionDto;

public interface SubscriptionService {

  // 구독
  SubscriptionDto create(UUID userId, UUID interestId);

  // 구독 취소
  void delete(UUID userId, UUID interestId);

  // 구독 여부 확인
  boolean existByUserIdAndInterestId(UUID userId, UUID interestId);

  // 구독중인 관심사 아이디 리스트
  List<UUID> findInterestIdsByUserId(UUID userId);
}
