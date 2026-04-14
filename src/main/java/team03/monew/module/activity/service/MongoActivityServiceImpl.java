package team03.monew.module.activity.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import team03.monew.module.activity.document.ActivityDocument;
import team03.monew.module.user.dto.ActivityDto;
import team03.monew.module.user.entity.User;
import team03.monew.module.activity.mapper.ActivityMapper;
import team03.monew.module.activity.repository.ActivityRepository;
import team03.monew.module.user.repository.UserRepository;
import team03.monew.module.user.exception.UserNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "add.db-type", havingValue = "mongodb")
public class MongoActivityServiceImpl implements ActivityService {

  private final ActivityRepository activityRepository;
  private final UserRepository userRepository;
  private final ActivityMapper activityMapper;

  @Override
  public ActivityDto findUserActivity(UUID userId) {
    log.debug("사용자 활동 내역 조회 시작: 사용자 ID = {}", userId);

    ActivityDocument activityDocument = activityRepository.findByUserId(userId)
        .orElseGet(() -> {
          User user = userRepository.findById(userId)
              .orElseThrow(() -> {
                log.error("존재하지 않는 사용자 ID: {}", userId);
                return UserNotFoundException.withId(userId);
              });

          ActivityDocument newActivityDocument = ActivityDocument.builder()
              .userId(userId)
              .email(user.getEmail())
              .nickname(user.getNickname())
              .createdAt(user.getCreatedAt())
              .build();

          activityRepository.save(newActivityDocument);
          return newActivityDocument;
        });

    ActivityDto userActivityDto = activityMapper.toDto(activityDocument);

    log.info("사용자 활동 내역 조회 완료: 사용자 ID = {}", userId);
    return userActivityDto;
  }
}
