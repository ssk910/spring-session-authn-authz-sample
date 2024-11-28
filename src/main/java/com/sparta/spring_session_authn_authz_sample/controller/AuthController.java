package com.sparta.spring_session_authn_authz_sample.controller;

import com.sparta.spring_session_authn_authz_sample.constants.SessionNames;
import com.sparta.spring_session_authn_authz_sample.dto.common.Authentication;
import com.sparta.spring_session_authn_authz_sample.dto.common.CommonResponseBody;
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

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 가입 및 로그인을 위한 API. </p>
 *
 * @author Seokgyu Hwang (Chris)
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  /**
   * 서비스.
   */
  private final AuthService authService;

  /**
   * 회원가입.
   *
   * @param requestDto {@link JoinRequestDto} 회원가입 DTO
   * @return {@code ResponseEntity<CommonResponseBody<?>>}
   */
  @PostMapping("/join")
  public ResponseEntity<CommonResponseBody<?>> join(@Valid @RequestBody JoinRequestDto requestDto) {
    this.authService.join(requestDto);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new CommonResponseBody<>("회원가입 완료."));
  }

  /**
   * 로그인.
   *
   * @param requestDto {@link LoginRequestDto} 로그인 DTO
   * @param request    {@link HttpServletRequest} 객체
   * @return {@code ResponseEntity<CommonResponseBody<?>>}
   */
  @PostMapping("/login")
  public ResponseEntity<CommonResponseBody<?>> login(@Valid @RequestBody LoginRequestDto requestDto,
      HttpServletRequest request) {
    Authentication authentication = this.authService.login(requestDto);

    HttpSession session = request.getSession();
    session.setAttribute(SessionNames.USER_AUTH, authentication);

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(new CommonResponseBody<>("로그인 성공."));
  }

  /**
   * 로그아웃.
   *
   * @param request {@link HttpServletRequest} 객체
   * @return {@code ResponseEntity<CommonResponseBody<?>>}
   */
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
