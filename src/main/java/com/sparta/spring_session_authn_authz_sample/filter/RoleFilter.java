/*
 * Created by Hochan Son on 2024. 11. 28.
 * As part of Bigin
 *
 * Copyright (C) Bigin (https://bigin.io/main) - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Dev Backend Team <hochan@bigin.io>, 2024. 11. 28.
 */

package com.sparta.spring_session_authn_authz_sample.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spring_session_authn_authz_sample.constants.GlobalConstants;
import com.sparta.spring_session_authn_authz_sample.dto.Authentication;
import com.sparta.spring_session_authn_authz_sample.entity.Role;
import com.sparta.spring_session_authn_authz_sample.exception.GlobalExceptionHandler;
import com.sparta.spring_session_authn_authz_sample.exception.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * create on 2024. 11. 28..
 * create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
public class RoleFilter extends AbstractFilter {
  private Role role;

  public RoleFilter(ObjectMapper objectMapper, GlobalExceptionHandler exceptionHandler, Role role) {
    super(objectMapper, exceptionHandler);
    this.role = role;
  }


  @Override
  protected void check(ServletRequest servletRequest, ServletResponse servletResponse) {
    HttpSession session = findHttpSession(servletRequest);
    Authentication authentication = (Authentication) session.getAttribute(
            GlobalConstants.USER_AUTH);

    Role clientRole = authentication.getRole();
    if (clientRole != this.role) {
      throw new UnauthorizedException(HttpStatus.UNAUTHORIZED, role.getName() + " 권한이 필요합니다.");
    }
  }
}
