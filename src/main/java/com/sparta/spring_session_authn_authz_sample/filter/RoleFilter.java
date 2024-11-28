package com.sparta.spring_session_authn_authz_sample.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spring_session_authn_authz_sample.constants.GlobalConstants;
import com.sparta.spring_session_authn_authz_sample.dto.Authentication;
import com.sparta.spring_session_authn_authz_sample.entity.Role;
import com.sparta.spring_session_authn_authz_sample.exception.GlobalExceptionHandler;
import com.sparta.spring_session_authn_authz_sample.exception.UnauthorizedException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 권한 확인을 위한 필터. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
public class RoleFilter extends AbstractFilter {

  private final Role role;

  public RoleFilter(ObjectMapper objectMapper, GlobalExceptionHandler exceptionHandler, Role role) {
    super(objectMapper, exceptionHandler);
    this.role = role;
  }


  @Override
  protected void check(ServletRequest servletRequest, ServletResponse servletResponse) {
    HttpSession session = findHttpSession(servletRequest);

    Authentication authentication = (Authentication) session.getAttribute(
        GlobalConstants.USER_AUTH);

    Role clientRole = authentication.getRole();
    if (clientRole != this.role) {
      throw new UnauthorizedException(HttpStatus.UNAUTHORIZED, role.getName() + " 권한이 필요합니다.");
    }
  }
}