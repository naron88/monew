package com.monew.util.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

  private final Instant timestamp; // 시간
  private final String code; // 커스텀 코드 ex) USER_001
  private final String message; // 에러 메세지
  private final Map<String, Object> details; // 세부 사항 ex) 입력 값 등
  private final String exceptionType; // 예외 유형
  private final int status; // 상태 코드

  public ErrorResponse(Exception e) {
    this(
        Instant.now(),
        // e.getClass().getName(): 전체 클래스 경로를 반환
        // e.getClass().getSimpleName(): 클래스 이름만 반환
        e.getClass().getSimpleName(),
        e.getMessage(),
        new HashMap<>(),
        e.getClass().getSimpleName(),
        HttpStatus.INTERNAL_SERVER_ERROR.value()
    );
  }

  public ErrorResponse(MonewException e) {
    this(
        Instant.now(),
        e.getErrorCode().name(),
        e.getMessage(),
        e.getDetails(),
        e.getClass().getSimpleName(),
        e.getErrorCode().getHttpStatus().value()
    );
  }
}
