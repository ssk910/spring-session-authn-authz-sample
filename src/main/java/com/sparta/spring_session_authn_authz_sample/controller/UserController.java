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

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 유저 권한 API. </p>
 *
 * @author Seokgyu Hwang (Chris)
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  /**
   * 서비스.
   */
  private final UserService userService;

  /**
   * 현재 유저의 정보를 리턴합니다.
   *
   * @param request {@link HttpServletRequest} 객체
   * @return {@code ResponseEntity<CommonResponseBody<MemberResponseDto>>}
   */
  @GetMapping("/introduce")
  public ResponseEntity<CommonResponseBody<MemberResponseDto>> introduce(
      HttpServletRequest request) {
    HttpSession session = request.getSession();
    Authentication authentication = (Authentication) session.getAttribute(SessionNames.USER_AUTH);

    MemberResponseDto responseDto = userService.performLogic(authentication.getUsername());

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(new CommonResponseBody<>("회원님의 정보입니다.", responseDto));
  }
}
