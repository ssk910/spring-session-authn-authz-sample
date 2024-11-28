package com.sparta.spring_session_authn_authz_sample.config.filter;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 로그인 확인을 위한 필터. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
public class AuthFilter implements CommonAuthFilter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain)
      throws IOException, ServletException {
    findHttpSession(servletRequest);
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
