package com.sparta.spring_session_authn_authz_sample.config.filter;

import com.sparta.spring_session_authn_authz_sample.constants.SessionNames;
import com.sparta.spring_session_authn_authz_sample.dto.Authentication;
import com.sparta.spring_session_authn_authz_sample.entity.Role;
import com.sparta.spring_session_authn_authz_sample.exception.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 권한 확인을 위한 필터. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@RequiredArgsConstructor
public class RoleFilter implements CommonAuthFilter {

  /**
   * 권한.
   */
  private final Role role;

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpSession session = findHttpSession(servletRequest);

    Authentication authentication = (Authentication) session.getAttribute(
        SessionNames.USER_AUTH);

    Role clientRole = authentication.getRole();
    if (clientRole != this.role) {
      throw new UnauthorizedException(HttpStatus.UNAUTHORIZED, role.getName() + " 권한이 필요합니다.");
    }

    filterChain.doFilter(servletRequest, servletResponse);
  }
}
