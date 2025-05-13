package com.monew.domain.user.service;

import com.monew.domain.user.dto.UserDto;
import com.monew.domain.user.dto.UserLoginRequest;

public interface AuthService {

  // 로그인
  UserDto login(UserLoginRequest request);

}
