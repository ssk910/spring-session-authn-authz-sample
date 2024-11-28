package com.sparta.spring_session_authn_authz_sample.controller;

import com.sparta.spring_session_authn_authz_sample.constants.SessionNames;
import com.sparta.spring_session_authn_authz_sample.dto.common.Authentication;
import com.sparta.spring_session_authn_authz_sample.dto.common.CommonResponseBody;
import com.sparta.spring_session_authn_authz_sample.dto.MemberResponseDto;
import com.sparta.spring_session_authn_authz_sample.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/introduce")
  public ResponseEntity<CommonResponseBody<MemberResponseDto>> introduce(
      HttpServletRequest request) {
    HttpSession session = request.getSession();
    Authentication authentication = (Authentication) session.getAttribute(
        SessionNames.USER_AUTH);

    MemberResponseDto responseDto = userService.performLogic(authentication.getUsername());

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(new CommonResponseBody<>("회원님의 정보입니다.", responseDto));
  }
}
