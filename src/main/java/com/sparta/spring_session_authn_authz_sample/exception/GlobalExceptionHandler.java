package com.sparta.spring_session_authn_authz_sample.exception;

import com.sparta.spring_session_authn_authz_sample.dto.common.CommonResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 통일된 포맷으로 예외를 처리하기 위한 핸들러. </p>
 *
 * @author Seokgyu Hwang (Chris)
 * @version 1.0
 * @since 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * {@link UnauthorizedException} 예외 처리.
   *
   * @param e {@code UnauthorizedException} 객체
   * @return {@code ResponseEntity<CommonResponseBody<?>>}
   */
  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<CommonResponseBody<?>> handleUnauthorizedException(
      UnauthorizedException e) {
    return ResponseEntity
        .status(e.getStatusCode())
        .body(new CommonResponseBody<>(e.getReason()));
  }
}
