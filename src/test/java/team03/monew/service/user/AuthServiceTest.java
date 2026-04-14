package team03.monew.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import team03.monew.module.user.dto.UserDto;
import team03.monew.module.user.dto.UserLoginRequest;
import team03.monew.module.user.entity.User;
import team03.monew.module.user.entity.User.Role;
import team03.monew.module.user.mapper.UserMapper;
import team03.monew.module.user.repository.UserRepository;
import team03.monew.module.user.service.AuthServiceImpl;
import team03.monew.module.user.exception.InvalidException;
import team03.monew.module.user.exception.UserNotFoundException;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

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
    User user = new User("user", "user@gmail.com", "qwer1234", Role.USER);
    UserDto userDto = new UserDto(user.getId(), user.getEmail(), user.getNickname(),
        user.getCreatedAt(), Role.USER.toString());
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
    User user = new User("user", "user@gmail.com", "qwer1234", Role.USER);
    given(userRepository.findByEmail(request.email())).willReturn(Optional.of(user));

    // when, then
    assertThrows(InvalidException.class, () -> authService.login(request));
  }
}