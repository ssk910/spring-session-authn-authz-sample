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
import com.sparta.spring_session_authn_authz_sample.dto.CommonResponseBody;
import com.sparta.spring_session_authn_authz_sample.exception.GlobalExceptionHandler;
import com.sparta.spring_session_authn_authz_sample.exception.UnauthorizedException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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
public abstract class AbstractFilter implements CommonAuthFilter {

  protected abstract void check(ServletRequest servletRequest, ServletResponse servletResponse);

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    try {
      check(servletRequest, servletResponse);
      filterChain.doFilter(servletRequest, servletResponse);
    } catch (UnauthorizedException e) {
      convertErrorResponse(servletResponse, e);
    }
  }

  void convertErrorResponse(ServletResponse httpServletResponse, UnauthorizedException e) throws IOException, ServletException {
    ObjectMapper objectMapper = new ObjectMapper();
    GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();
    ResponseEntity<CommonResponseBody<?>> responseEntity = exceptionHandler.handleOtherExceptions(e);

    HttpServletResponse response = (HttpServletResponse) httpServletResponse;
    response.setStatus(responseEntity.getStatusCodeValue());

    responseEntity.getHeaders().forEach((key, values) ->
            values.forEach(value -> response.addHeader(key, value))
    );

    String jsonResponse = objectMapper.writeValueAsString(responseEntity.getBody());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(jsonResponse);
  }
}
