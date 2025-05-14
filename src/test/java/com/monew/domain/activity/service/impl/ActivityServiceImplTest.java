package com.monew.domain.activity.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import com.monew.domain.activity.entity.Activity;
import com.monew.domain.activity.repository.ActivityRepository;
import com.monew.domain.user.entity.User;
import com.monew.global.exception.activity.custom.ActivityAlreadyExistsException;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ActivityServiceImplTest {

  @InjectMocks
  ActivityServiceImpl activityService;

  @Mock
  ActivityRepository activityRepository;

  @Nested
  @DisplayName("활동 내역 생성")
  class createActivity {

    @Test
    @DisplayName("활동 내역 생성 성공")
    void createActivity_success() {
      // given
      UUID userId = UUID.randomUUID();
      User user = spy(new User("test", "test@gmail.com", "qwer12"));
      Activity activity = new Activity(user);

      doReturn(userId).when(user).getId();

      given(activityRepository.existsByUserId(userId)).willReturn(false);
      given(activityRepository.save(any(Activity.class))).willReturn(activity);

      // when
      Activity result = activityService.create(user);

      // then
      assertNotNull(result);
      assertEquals(result.getUser().getNickname(), activity.getUser().getNickname());
      then(activityRepository).should().save(any(Activity.class));

    }

    @Test
    @DisplayName("활동 내역 생성 실패 - 이미 같은 userId의 활동 내역 존재")
    void createActivity_ActivityAlreadyExists() {
      // given
      UUID userId = UUID.randomUUID();
      User user = spy(new User("test", "test@gmail.com", "qwer12"));

      doReturn(userId).when(user).getId();

      given(activityRepository.existsByUserId(userId)).willReturn(true);

      // when, then
      assertThrows(ActivityAlreadyExistsException.class, () -> activityService.create(user));
    }
  }
}