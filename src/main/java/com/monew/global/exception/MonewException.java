package com.monew.global.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public class MonewException extends RuntimeException {

  private final Instant timestamp;
  private final ErrorCode errorCode;
  private final Map<String, Object> details;

  public MonewException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.timestamp = Instant.now();
    this.errorCode = errorCode;
    this.details = new HashMap<>();
  }

  // 커스텀 예외를 사용하지만 원래 에러 로그를 알고 싶을 때 사용
  public MonewException(ErrorCode errorCode, Throwable cause) {
    super(errorCode.getMessage(), cause);
    this.timestamp = Instant.now();
    this.errorCode = errorCode;
    this.details = new HashMap<>();
  }

  public void addDetail(String key, Object value) {
    this.details.put(key, value);
  }
}
