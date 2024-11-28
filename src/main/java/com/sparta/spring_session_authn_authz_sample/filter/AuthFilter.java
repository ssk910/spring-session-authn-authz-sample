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
import com.sparta.spring_session_authn_authz_sample.exception.GlobalExceptionHandler;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
public class AuthFilter extends AbstractFilter {

  public AuthFilter(ObjectMapper objectMapper, GlobalExceptionHandler exceptionHandler) {
    super(objectMapper, exceptionHandler);
  }

  @Override
  protected void check(ServletRequest servletRequest, ServletResponse servletResponse) {
    findHttpSession(servletRequest);
  }
}
