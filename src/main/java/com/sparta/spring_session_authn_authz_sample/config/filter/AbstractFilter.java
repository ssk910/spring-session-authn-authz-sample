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
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 세션을 이용한 공통 필터. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
public abstract class AbstractFilter implements SessionFilter {

  /**
   * Object mapper.
   */
  private final ObjectMapper objectMapper;

  /**
   * 예외 핸들러.
   */
  private final GlobalExceptionHandler exceptionHandler;

  /**
   * 생성자.
   *
   * @param objectMapper {@code ObjectMapper}
   * @param exceptionHandler {@code GlobalExceptionHandler}
   */
  public AbstractFilter(ObjectMapper objectMapper, GlobalExceptionHandler exceptionHandler) {
    this.objectMapper = objectMapper;
    this.exceptionHandler = exceptionHandler;
  }

  /**
   * 인가를 수행합니다.
   *
   * @param servletRequest  {@code ServletRequest} 객체
   * @param servletResponse {@code ServletResponse} 객체
   */
  protected abstract void authorize(ServletRequest servletRequest, ServletResponse servletResponse);

  /**
   * <p>인가를 수행하고, 통과되지 못하면 {@link GlobalExceptionHandler}를 이용해 에러 메세지를 담아 response를 보냅니다.</p>
   * {@inheritDoc}
   */
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    try {
      authorize(servletRequest, servletResponse);
      filterChain.doFilter(servletRequest, servletResponse);
    } catch (UnauthorizedException e) {
      writeResponse(servletResponse, exceptionHandler.handleUnauthorizedException(e));
    }
  }

  /**
   * 입력받은 {@code responseEntity}를 body로 갖는 response를 만듭니다.
   *
   * @param servletResponse {@code ServletResponse} 객체
   * @param responseEntity  response body에 담길 {@code ResponseEntity<CommonResponseBody<?>>} 객체
   * @throws IOException response 생성에 실패했을 경우
   */
  void writeResponse(ServletResponse servletResponse,
      ResponseEntity<CommonResponseBody<?>> responseEntity) throws IOException {
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    response.setStatus(responseEntity.getStatusCode().value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());

    response.getWriter().write(objectMapper.writeValueAsString(responseEntity.getBody()));
  }
}
