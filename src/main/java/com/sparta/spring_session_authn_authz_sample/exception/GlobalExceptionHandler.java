package com.sparta.spring_session_authn_authz_sample.exception;

import com.sparta.spring_session_authn_authz_sample.dto.CommonResponseBody;
import jakarta.servlet.ServletException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<CommonResponseBody<?>> handleOtherExceptions(UnauthorizedException e) {
    return ResponseEntity
        .status(e.getStatusCode())
        .body(new CommonResponseBody<>(e.getReason()));
  }
}
