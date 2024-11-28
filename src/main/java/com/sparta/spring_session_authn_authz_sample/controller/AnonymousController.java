package com.sparta.spring_session_authn_authz_sample.controller;

import com.sparta.spring_session_authn_authz_sample.dto.common.CommonResponseBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/anonymous")
@RequiredArgsConstructor
public class AnonymousController {

  @GetMapping("/introduce")
  public ResponseEntity<CommonResponseBody<?>> introduce(HttpServletRequest request) {
    HttpSession session = request.getSession(false);

    String responseMessage = session == null
        ? "당신은 로그인하지 않은 사용자입니다."
        : "당신은 로그인한 사용자입니다.";

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(new CommonResponseBody<>(responseMessage));
  }
}
