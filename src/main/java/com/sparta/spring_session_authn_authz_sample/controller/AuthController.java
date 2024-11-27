package com.sparta.spring_session_authn_authz_sample.controller;

import com.sparta.spring_session_authn_authz_sample.constants.GlobalConstants;
import com.sparta.spring_session_authn_authz_sample.dto.Authentication;
import com.sparta.spring_session_authn_authz_sample.dto.CommonResponseBody;
import com.sparta.spring_session_authn_authz_sample.dto.JoinRequestDto;
import com.sparta.spring_session_authn_authz_sample.dto.LoginRequestDto;
import com.sparta.spring_session_authn_authz_sample.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/join")
  public ResponseEntity<CommonResponseBody<?>> join(@Valid @RequestBody JoinRequestDto requestDto) {
    this.authService.join(requestDto);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new CommonResponseBody<>("회원가입 완료."));
  }

  @PostMapping("/login")
  public ResponseEntity<CommonResponseBody<?>> login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest request) {
    Authentication authentication = this.authService.login(requestDto);

    HttpSession session = request.getSession();
    session.setAttribute(GlobalConstants.USER_AUTH, authentication);

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(new CommonResponseBody<>("로그인 성공."));
  }

  @PostMapping("/logout")
  public ResponseEntity<CommonResponseBody<?>> logout(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate(); // 세션 무효화
    }

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(new CommonResponseBody<>("로그아웃 성공"));
  }
}
