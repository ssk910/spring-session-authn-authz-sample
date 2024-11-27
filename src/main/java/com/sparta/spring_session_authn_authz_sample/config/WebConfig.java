package com.sparta.spring_session_authn_authz_sample.config;

import com.sparta.spring_session_authn_authz_sample.interceptor.AdminRoleInterceptor;
import com.sparta.spring_session_authn_authz_sample.interceptor.AuthInterceptor;
import com.sparta.spring_session_authn_authz_sample.interceptor.UserRoleInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  private static final String[] AUTH_REQUIRED_PATH_PATTERNS = {"/api/auth/logout", "/api/admins/*",
      "/api/users/*"};
  private static final String[] ADMIN_ROLE_REQUIRED_PATH_PATTERNS = {"/api/admins/*"};
  private static final String[] USER_ROLE_REQUIRED_PATH_PATTERNS = {"/api/users/*"};

  private final AuthInterceptor authInterceptor;
  private final AdminRoleInterceptor adminRoleInterceptor;
  private final UserRoleInterceptor userRoleInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(authInterceptor)
        .addPathPatterns(AUTH_REQUIRED_PATH_PATTERNS)
        .order(Ordered.HIGHEST_PRECEDENCE);

    registry.addInterceptor(adminRoleInterceptor)
        .addPathPatterns(ADMIN_ROLE_REQUIRED_PATH_PATTERNS)
        .order(Ordered.HIGHEST_PRECEDENCE + 1);

    registry.addInterceptor(userRoleInterceptor)
        .addPathPatterns(USER_ROLE_REQUIRED_PATH_PATTERNS)
        .order(Ordered.HIGHEST_PRECEDENCE + 2);
  }
}