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

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 권한이 필요 없는 API. </p>
 *
 * @author Seokgyu Hwang (Chris)
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/anonymous")
@RequiredArgsConstructor
public class AnonymousController {

  /**
   * 현재 사용자의 로그인 여부를 응답에 담아 리턴합니다.
   *
   * @param request {@code HttpServletRequest} 객체
   * @return {@code ResponseEntity<CommonResponseBody<?>>}
   */
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
