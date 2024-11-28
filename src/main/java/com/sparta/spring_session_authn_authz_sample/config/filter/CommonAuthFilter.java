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

  default HttpSession findHttpSession(ServletRequest request) {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    return Optional.of(httpServletRequest.getSession(false))
        .orElseThrow(() -> new UnauthorizedException(HttpStatus.UNAUTHORIZED, "로그인 필요"));
  }
}
