/*
 * Created by Hochan Son on 2024. 11. 28.
 * As part of Bigin
 *
 * Copyright (C) Bigin (https://bigin.io/main) - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Dev Backend Team <hochan@bigin.io>, 2024. 11. 28.
 */

package com.sparta.spring_session_authn_authz_sample.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spring_session_authn_authz_sample.dto.common.CommonResponseBody;
import com.sparta.spring_session_authn_authz_sample.exception.GlobalExceptionHandler;
import com.sparta.spring_session_authn_authz_sample.exception.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * create on 2024. 11. 28.
 * create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
public abstract class AbstractFilter implements AuthFilter {

  private final ObjectMapper objectMapper;
  private final GlobalExceptionHandler exceptionHandler;

  public AbstractFilter(ObjectMapper objectMapper, GlobalExceptionHandler exceptionHandler) {
    this.objectMapper = objectMapper;
    this.exceptionHandler = exceptionHandler;
  }

  protected abstract void check(ServletRequest servletRequest, ServletResponse servletResponse);

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    try {
      check(servletRequest, servletResponse);
      filterChain.doFilter(servletRequest, servletResponse);
    } catch (UnauthorizedException e) {
      convertErrorResponse(servletResponse, exceptionHandler.handleUnauthorizedException(e));
    }
  }

  void convertErrorResponse(ServletResponse httpServletResponse, ResponseEntity<CommonResponseBody<?>> responseEntity) throws IOException {
    HttpServletResponse response = (HttpServletResponse) httpServletResponse;
    response.setStatus(responseEntity.getStatusCode().value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
    response.getWriter().write(objectMapper.writeValueAsString(responseEntity.getBody()));
  }
}
