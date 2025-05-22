package com.monew.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

  // user 에러 코드
  USER_NOT_FOUND("USER_001", "사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  INVALID_USER_CREDENTIALS("USER_002", "잘못된 비밀번호입니다.", HttpStatus.UNAUTHORIZED),
  DUPLICATE_USER("USER_003", "중복된 사용자입니다.", HttpStatus.CONFLICT),

  // activity 에러 코드
  DUPLICATE_ACTIVITY("ACTIVITY_003", "중복된 활동 내역입니다.", HttpStatus.CONFLICT),

  // interest 에러 코드
  INTEREST_NOT_FOUND("INTEREST_001", "관심사를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  DUPLICATE_INTEREST("INTEREST_003", "중복된 관심사 이름입니다.", HttpStatus.CONFLICT),

  // Server 에러 코드
  INTERNAL_SERVER_ERROR("SERVER_001", "서버 내부 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

  // Request 에러 코드
  INVALID_REQUEST("REQUEST_001", "잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
  FORBIDDEN_REQUEST("REQUEST_002", "접근 권한이 없습니다.", HttpStatus.FORBIDDEN);

  private final String code;
  private final String message;
  private final HttpStatus httpStatus;

  ErrorCode(String code, String message, HttpStatus httpStatus) {
    this.code = code;
    this.message = message;
    this.httpStatus = httpStatus;
  }
}
