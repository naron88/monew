package com.monew.domain.user.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.monew.domain.user.dto.UserDto;
import com.monew.domain.user.dto.UserLoginRequest;
import com.monew.domain.user.entity.User;
import com.monew.domain.user.repository.UserRepository;
import com.monew.global.exception.user.custom.InvalidException;
import com.monew.global.exception.user.custom.UserNotFoundException;
import com.monew.global.util.mapper.user.UserMapper;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {
  @InjectMocks
  private AuthServiceImpl authService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private UserMapper userMapper;

  @Test
  @DisplayName("로그인 성공")
  void login_success() {
    // given
    UserLoginRequest request = new UserLoginRequest("user@gmail.com", "qwer1234");
    User user = new User("user", "user@gmail.com", "qwer1234");
    UserDto userDto = new UserDto(user.getId(), user.getEmail(), user.getNickname(),
        user.getCreatedAt());
    given(userRepository.findByEmail(request.email())).willReturn(Optional.of(user));
    given(userMapper.toDto(user)).willReturn(userDto);

    // when
    UserDto result = authService.login(request);

    // then
    assertNotNull(result);
    assertEquals(result.email(), user.getEmail());
    then(userRepository).should().findByEmail(request.email());
  }

  @Test
  @DisplayName("유저 찾기 실패 - user not found")
  void login_userNotFound() {
    // given
    UserLoginRequest request = new UserLoginRequest("fail@gmail.com", "qwer1234");
    given(userRepository.findByEmail(request.email())).willReturn(Optional.empty());

    // when, then
    assertThrows(UserNotFoundException.class, () -> authService.login(request));
  }

  @Test
  @DisplayName("비밀번호 물일치")
  void login_invalidPassword() {
    // given
    UserLoginRequest request = new UserLoginRequest("user@gmail.com", "fail");
    User user = new User("user", "user@gmail.com", "qwer1234");
    given(userRepository.findByEmail(request.email())).willReturn(Optional.of(user));

    // when, then
    assertThrows(InvalidException.class, () -> authService.login(request));
  }
}