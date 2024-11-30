package com.sparta.spring_session_authn_authz_sample.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spring_session_authn_authz_sample.config.filter.BaseAuthFilter;
import com.sparta.spring_session_authn_authz_sample.config.filter.RoleFilter;
import com.sparta.spring_session_authn_authz_sample.entity.Role;
import com.sparta.spring_session_authn_authz_sample.exception.GlobalExceptionHandler;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 웹 설정. </p>
 * <p> {@link WebMvcConfigurer}를 구현하여 인터셉터를 등록하합니다. </p>
 *
 * @author Seokgyu Hwang (Chris)
 * @version 1.0
 * @since 1.0
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig {

  private final ObjectMapper objectMapper;
  private final GlobalExceptionHandler exceptionHandler;

  private static final String[] AUTH_REQUIRED_PATH_PATTERNS = {"/api/auth/logout", "/api/admins/*",
      "/api/users/*"};
  private static final String[] ADMIN_ROLE_REQUIRED_PATH_PATTERNS = {"/api/admins/*"};
  private static final String[] USER_ROLE_REQUIRED_PATH_PATTERNS = {"/api/users/*"};

  /*
   * 아래의 필터들은 인터셉터를 대신할 수 있도록 작성한 코드입니다.
   * 사용하려면 위 addInterceptors() 메소드를 주석 처리한 후, 아래 코드들의 주석을 풀어주세요.
   */
  @Bean
  public FilterRegistrationBean baseAuthFilter() {
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new BaseAuthFilter(objectMapper, exceptionHandler));
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