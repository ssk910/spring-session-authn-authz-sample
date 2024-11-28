package com.sparta.spring_session_authn_authz_sample.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 인가되지 않았을 경우 던지는 예외. </p>
 *
 * @author Seokgyu Hwang (Chris)
 * @version 1.0
 * @since 1.0
 */
public class UnauthorizedException extends ResponseStatusException {

  /**
   * 생성자.
   *
   * @param status 상태코드
   * @param reason 예외 발생 사유
   */
  public UnauthorizedException(HttpStatusCode status, String reason) {
    super(status, reason);
  }
}
