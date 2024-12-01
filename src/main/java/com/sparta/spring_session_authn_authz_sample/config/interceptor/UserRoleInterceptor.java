package com.sparta.spring_session_authn_authz_sample.config.interceptor;

import com.sparta.spring_session_authn_authz_sample.constants.SessionNames;
import com.sparta.spring_session_authn_authz_sample.dto.common.Authentication;
import com.sparta.spring_session_authn_authz_sample.entity.Role;
import com.sparta.spring_session_authn_authz_sample.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 유저 권한 인터셉터. </p>
 *
 * @author Seokgyu Hwang (Chris)
 * @version 1.0
 * @since 1.0
 */
@Component
public class UserRoleInterceptor implements HandlerInterceptor {

  /**
   * 유저 권한을 확인합니다.
   *
   * @param request  {@code HttpServletRequest}
   * @param response {@code HttpServletResponse}
   * @param handler  실행하기 위해 선택된 핸들러
   * @return 인터셉터 체이닝 여부
   * <ul>
   *  <li>{@code true} - 다음 인터셉터 또는 핸들러를 실행.</li>
   *  <li>{@code false} - 다음 인터셉터를 실행하지 않고 중단.</li>
   * </ul>
   * @throws UnauthorizedException 인가되지 않았을 경우
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws UnauthorizedException {
    HttpSession session = request.getSession(false);
    if (session == null) {
      throw new UnauthorizedException(HttpStatus.UNAUTHORIZED, "세션이 끊어졌습니다.");
    }

    Authentication authentication = (Authentication) session.getAttribute(SessionNames.USER_AUTH);
    Role role = authentication.getRole();

    if (role != Role.USER) {
      throw new UnauthorizedException(HttpStatus.UNAUTHORIZED, "user 권한이 필요합니다.");
    }

    return true;
  }
}

