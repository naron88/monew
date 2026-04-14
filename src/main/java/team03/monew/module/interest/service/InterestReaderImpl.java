package team03.monew.module.interest.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team03.monew.module.interest.entity.Interest;
import team03.monew.module.interest.repository.InterestRepository;
import team03.monew.module.interest.exception.InterestNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class InterestReaderImpl implements InterestReader {

  private final InterestRepository interestRepository;

  // 관심사 엔티티 반환
  @Override
  @Transactional(readOnly = true)
  public Interest getInterestEntityById(UUID interestId) {

    Interest interest = interestRepository.findById(interestId)
        .orElseThrow(() -> InterestNotFoundException.withInterestId(interestId));

    log.info("관심사 엔티티 반환: interestId={}, interestName={}", interest.getId(), interest.getName());

    return interest;
  }
}
