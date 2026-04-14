package team03.monew.module.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team03.monew.module.user.dto.UserDto;
import team03.monew.module.user.dto.UserLoginRequest;
import team03.monew.module.user.entity.User;
import team03.monew.module.user.mapper.UserMapper;
import team03.monew.module.user.repository.UserRepository;
import team03.monew.module.user.exception.InvalidException;
import team03.monew.module.user.exception.UserNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public UserDto login(UserLoginRequest request) {
    log.debug("로그인 시작: email={}", request.email());
    // email로 유저 확인
    User user = userRepository.findByEmail(request.email())
        .orElseThrow(() -> UserNotFoundException.withEmail(request.email()));

    // 소프트 삭제된 유저일 경우
    if (user.isDeleted()) {
      throw UserNotFoundException.isDeleted();
    }

    // password 확인
    if (!user.getPassword().equals(request.password())) {
      throw InvalidException.wrongPassword();
    }

    log.info("로그인 완료: userId={}, email={}", user.getId(), user.getEmail());
    return userMapper.toDto(user);
  }
}
