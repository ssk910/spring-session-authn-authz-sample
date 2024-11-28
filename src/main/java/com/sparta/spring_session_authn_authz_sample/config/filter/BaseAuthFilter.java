package com.sparta.spring_session_authn_authz_sample.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spring_session_authn_authz_sample.exception.GlobalExceptionHandler;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 로그인 확인을 위한 필터. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
public class BaseAuthFilter extends AbstractFilter {

  public BaseAuthFilter(ObjectMapper objectMapper, GlobalExceptionHandler exceptionHandler) {
    super(objectMapper, exceptionHandler);
  }

  protected void check(ServletRequest servletRequest, ServletResponse servletResponse) {
    findHttpSession(servletRequest);
  }
}
