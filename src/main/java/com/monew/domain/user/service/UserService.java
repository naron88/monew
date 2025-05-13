package com.monew.domain.user.service;

import com.monew.domain.user.dto.UserDto;
import com.monew.domain.user.dto.UserRegisterRequest;
import com.monew.domain.user.dto.UserUpdateRequest;
import com.monew.domain.user.entity.User;
import java.util.List;
import java.util.UUID;

public interface UserService {

  // 1. 사용자 등록(회원 가입)
  UserDto create(UserRegisterRequest request);

  // 2. 모든 사용자 정보 조회
  List<UserDto> findAll();

  // 3. 특정 사용자 정보 조회 - UserDto
  UserDto findUserDtoById(UUID id);

  // 3.1 특정 사용자 정보 조회 - User
  User findUserById(UUID id);

  // 4. 사용자 정보 수정
  UserDto update(UUID id, UserUpdateRequest request);

  // 5. 사용자 논리 삭제
  void softDelete(UUID id);

  // 6. 사용자 물리 삭제
  void hardDelete(UUID id);
}
