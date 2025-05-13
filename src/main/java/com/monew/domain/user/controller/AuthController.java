package com.monew.domain.user.controller;

import com.monew.domain.user.dto.UserDto;
import com.monew.domain.user.dto.UserLoginRequest;
import com.monew.domain.user.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<UserDto> login(
      @RequestBody @Valid UserLoginRequest request,
      HttpSession session) {
    log.info("로그인 요청: email={}", request.email());
    UserDto userDto = authService.login(request);
    log.debug("로그인 응답: {}", userDto);

    log.debug("세션 저장: userId={}", userDto.id());
    session.setAttribute("userId", userDto.id());
    return ResponseEntity.status(HttpStatus.OK).body(userDto);
  }
}
