package com.monew.domain.user.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.monew.domain.user.dto.UserDto;
import com.monew.domain.user.dto.UserRegisterRequest;
import com.monew.domain.user.dto.UserUpdateRequest;
import com.monew.domain.user.entity.User;
import com.monew.domain.user.repository.UserRepository;
import com.monew.global.exception.user.custom.UserAlreadyExistsException;
import com.monew.global.exception.user.custom.UserNotFoundException;
import com.monew.global.util.mapper.user.UserMapper;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private UserMapper userMapper;

  @Test
  @DisplayName("모든 사용자 조회")
  void findAll_success() {
    // given
    User user1 = new User("user1", "user1@gmail.com", "qwer1234");
    User user2 = new User("user2", "user2@gmail.com", "qwer1234");

    List<User> users = List.of(user1, user2);

    UserDto user1Dto = new UserDto(user1.getId(), user1.getEmail(), user1.getNickname(),
        user1.getCreatedAt());
    UserDto user2Dto = new UserDto(user2.getId(), user2.getEmail(), user2.getNickname(),
        user2.getCreatedAt());

    given(userRepository.findAll()).willReturn(users);
    given(userMapper.toDto(user1)).willReturn(user1Dto);
    given(userMapper.toDto(user2)).willReturn(user2Dto);

    // when
    List<UserDto> result = userService.findAll();

    // then
    assertNotNull(result);
    assertEquals(result.get(0).email(), user1Dto.email());
    assertEquals(result.get(1).email(), user2Dto.email());

    then(userRepository).should().findAll();
    then(userMapper).should().toDto(user1);
    then(userMapper).should().toDto(user2);
  }

  @Nested
  @DisplayName("사용자 등록")
  class CreateUserTest {

    @Test
    @DisplayName("사용자 등록 성공")
    void createUser_success() {
      // given
      UserRegisterRequest request = new UserRegisterRequest("test@gmail.com", "test", "qwer1234");
      User user = new User(request.nickname(), request.email(), request.password());
      UserDto userDto = new UserDto(user.getId(), user.getEmail(), user.getNickname(),
          user.getCreatedAt());

      given(userRepository.existsByEmail(user.getEmail())).willReturn(false);
      given(userRepository.save(any(User.class))).willReturn(user);
      given(userMapper.toDto(user)).willReturn(userDto);

      // when
      UserDto result = userService.create(request);

      // then
      assertNotNull(result);
      assertEquals(result.email(), userDto.email());
      assertEquals(result.nickname(), userDto.nickname());
      then(userRepository).should().save(any(User.class));
      then(userMapper).should().toDto(user);
    }

    @Test
    @DisplayName("이메일 중복")
    void createUser_DuplicateEmail() {
      // given
      UserRegisterRequest request = new UserRegisterRequest("test@gmail.com", "test", "qwer1234");
      given(userRepository.existsByEmail(request.email())).willReturn(true);

      // when, then
      assertThrows(UserAlreadyExistsException.class, () -> userService.create(request));
    }
  }

  @Nested
  @DisplayName("특정 사용자 조회 - Dto 반환")
  class FindUserDtoById {

    @Test
    @DisplayName("특정 사용자 조회 성공")
    void FindUserDtoById_success() {
      // given
      UUID userId = UUID.randomUUID();
      User user = new User("user", "user@gmail.com", "qwer1234");
      UserDto userDto = new UserDto(user.getId(), user.getEmail(), user.getNickname(),
          user.getCreatedAt());
      given(userRepository.findActiveById(userId)).willReturn(Optional.of(user));
      given(userMapper.toDto(user)).willReturn(userDto);

      // when
      UserDto result = userService.findUserDtoById(userId);

      // then
      assertNotNull(result);
      assertEquals(result.id(), userDto.id());
      assertEquals(result.email(), userDto.email());

      then(userRepository).should().findActiveById(userId);
      then(userMapper).should().toDto(user);
    }

    @Test
    @DisplayName("사용자 찾기 실패 - user not found")
    void FindUserDtoById_userNotFound() {
      // given
      UUID userId = UUID.randomUUID();
      given(userRepository.findActiveById(userId)).willReturn(Optional.empty());

      // when, then
      assertThrows(UserNotFoundException.class, () -> userService.findUserDtoById(userId));
    }
  }

  @Nested
  @DisplayName("특정 사용자 조회 - Entity")
  class FindUserById {

    @Test
    @DisplayName("특정 사용자 조회 성공")
    void FindUserById_success() {
      // given
      UUID userId = UUID.randomUUID();
      User user = new User("user", "user@gmail.com", "qwer1234");
      given(userRepository.findActiveById(userId)).willReturn(Optional.of(user));

      // when
      User result = userService.findUserById(userId);

      // then
      assertNotNull(result);
      assertEquals(result.getId(), user.getId());
      assertEquals(result.getEmail(), user.getEmail());

      then(userRepository).should().findActiveById(userId);
    }

    @Test
    @DisplayName("사용자 찾기 실패 - user not found")
    void FindUserById_userNotFound() {
      // given
      UUID userId = UUID.randomUUID();
      given(userRepository.findActiveById(userId)).willReturn(Optional.empty());

      // when, then
      assertThrows(UserNotFoundException.class, () -> userService.findUserById(userId));
    }
  }

  @Nested
  @DisplayName("사용자 정보 수정")
  class Update {

    @Test
    @DisplayName("사용자 정보 수정 성공")
    void update_success() {
      // given
      UUID id = UUID.randomUUID();
      UserUpdateRequest request = new UserUpdateRequest("updatedUser");
      User user = new User("user", "user@gmail.com", "qwer1234");
      UserDto userDto = new UserDto(user.getId(), user.getEmail(), request.nickname(),
          user.getCreatedAt());
      given(userRepository.findActiveById(id)).willReturn(Optional.of(user));
      given(userMapper.toDto(user)).willReturn(userDto);

      // when
      UserDto result = userService.update(id, request);

      // then
      assertNotNull(result);
      assertEquals(result.nickname(), user.getNickname());
      then(userRepository).should().findActiveById(id);
      then(userMapper).should().toDto(user);
    }

    @Test
    @DisplayName("사용자 정보 수정 실패 - user not found")
    void update_userNotFound() {
      // given
      UUID userId = UUID.randomUUID();
      UserUpdateRequest request = new UserUpdateRequest("updatedUser");
      given(userRepository.findActiveById(userId)).willReturn(Optional.empty());

      // when, then
      assertThrows(UserNotFoundException.class, () -> userService.update(userId, request));
    }
  }

  @Nested
  @DisplayName("사용자 논리 삭제")
  class SoftDelete {

    @Test
    @DisplayName("사용자 논리 삭제 성공")
    void softDelete_success() {
      // given
      UUID id = UUID.randomUUID();
      User user = new User("user", "user@gmail.com", "qwer1234");
      given(userRepository.findActiveById(id)).willReturn(Optional.of(user));

      // when
      userService.softDelete(id);

      // then
      assertTrue(user.isDeleted());
      assertNotNull(user.getDeletedAt());
    }

    @Test
    @DisplayName("사용자 논리 삭제 실패- user not found")
    void softDelete_userNotFound() {
      // given
      UUID id = UUID.randomUUID();
      given(userRepository.findActiveById(id)).willReturn(Optional.empty());

      // when, then
      assertThrows(UserNotFoundException.class, () -> userService.softDelete(id));
    }
  }

  @Nested
  @DisplayName("사용자 물리 삭제")
  class HardDelete {

    @Test
    @DisplayName("사용자 물리 삭제 성공")
    void hardDelete_success() {
      // given
      UUID id = UUID.randomUUID();
      User user = new User("user", "user@gmail.com", "qwer1234");
      given(userRepository.findById(id)).willReturn(Optional.of(user));

      // when
      userService.hardDelete(id);

      // then
      then(userRepository).should().delete(user);
    }

    @Test
    @DisplayName("사용자 물리 삭제 실패 - user not found")
    void hardDelete_userNotfound() {
      // given
      UUID id = UUID.randomUUID();
      given(userRepository.findById(id)).willReturn(Optional.empty());

      // when, then
      assertThrows(UserNotFoundException.class, () -> userService.hardDelete(id));
    }
  }
}