package team03.monew.module.user.service;

import team03.monew.module.user.dto.UserDto;
import team03.monew.module.user.dto.UserLoginRequest;

public interface AuthService {

  // 로그인
  UserDto login(UserLoginRequest request);

}
