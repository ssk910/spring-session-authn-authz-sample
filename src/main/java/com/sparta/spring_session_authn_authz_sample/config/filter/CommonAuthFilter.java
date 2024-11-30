package com.sparta.spring_session_authn_authz_sample.config.filter;

import com.sparta.spring_session_authn_authz_sample.exception.UnauthorizedException;
import jakarta.servlet.Filter;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;

import java.util.Optional;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 로그인 확인을 위한 공통 필터. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
public interface CommonAuthFilter extends Filter {

  /**
   * 입력받은 {@code ServletRequest} 객체와 연관된 세션을 리턴한다.
   *
   * @param request {@code ServletRequest} 객체
   * @return 연관된 {@link HttpSession} 객체
   * @throws UnauthorizedException 연관된 세션을 찾지 못한 경우
   */
  default HttpSession findHttpSession(ServletRequest request) throws UnauthorizedException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    return Optional.of(httpServletRequest.getSession(false))
        .orElseThrow(() -> new UnauthorizedException(HttpStatus.UNAUTHORIZED, "로그인 필요"));
  }
}
