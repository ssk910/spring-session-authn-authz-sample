package com.sparta.spring_session_authn_authz_sample.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spring_session_authn_authz_sample.entity.Role;
import com.sparta.spring_session_authn_authz_sample.exception.GlobalExceptionHandler;
import com.sparta.spring_session_authn_authz_sample.filter.AuthFilter;
import com.sparta.spring_session_authn_authz_sample.filter.RoleFilter;
import com.sparta.spring_session_authn_authz_sample.interceptor.AdminRoleInterceptor;
import com.sparta.spring_session_authn_authz_sample.interceptor.AuthInterceptor;
import com.sparta.spring_session_authn_authz_sample.interceptor.UserRoleInterceptor;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  private final ObjectMapper objectMapper;
  private final GlobalExceptionHandler exceptionHandler;

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

  @Bean
  public FilterRegistrationBean authFilter() {
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new AuthFilter(objectMapper, exceptionHandler));
    filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    filterRegistrationBean.addUrlPatterns(AUTH_REQUIRED_PATH_PATTERNS);
    return filterRegistrationBean;
  }

  @Bean
  public FilterRegistrationBean adminRoleFilter() {
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new RoleFilter(objectMapper, exceptionHandler, Role.ADMIN));
    filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
    filterRegistrationBean.addUrlPatterns(ADMIN_ROLE_REQUIRED_PATH_PATTERNS);
    return filterRegistrationBean;
  }

  @Bean
  public FilterRegistrationBean userRoleFilter() {
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new RoleFilter(objectMapper, exceptionHandler, Role.USER));
    filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 2);
    filterRegistrationBean.addUrlPatterns(USER_ROLE_REQUIRED_PATH_PATTERNS);
    return filterRegistrationBean;
  }
}