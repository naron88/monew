package team03.monew.module.interest.service;

import java.util.UUID;
import team03.monew.module.interest.entity.Interest;

public interface InterestReader {

  // 관심사 엔티티 반환
  Interest getInterestEntityById(UUID interestId);
}
