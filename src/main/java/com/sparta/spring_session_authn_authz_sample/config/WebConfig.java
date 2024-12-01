package com.sparta.spring_session_authn_authz_sample.config;

import com.sparta.spring_session_authn_authz_sample.config.interceptor.AdminRoleInterceptor;
import com.sparta.spring_session_authn_authz_sample.config.interceptor.AuthInterceptor;
import com.sparta.spring_session_authn_authz_sample.config.interceptor.UserRoleInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 웹 설정. </p>
 * <p> {@link WebMvcConfigurer}를 구현하여 인터셉터를 등록합니다. </p>
 *
 * @author Seokgyu Hwang (Chris)
 * @version 1.0
 * @since 1.0
 */
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

  /**
   * <p>인터셉터의 우선순위와 path 패턴을 적용.</p>
   * {@inheritDoc}
   */
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

  /*
   * 아래의 필터들은 인터셉터를 대신할 수 있도록 작성한 코드입니다.
   * 사용하려면 위 addInterceptors() 메소드를 주석 처리한 후, 아래 코드들의 주석을 풀어주세요.
   */
  /*
  @Bean
  public FilterRegistrationBean authFilter() {
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new AuthFilter());
    filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    filterRegistrationBean.addUrlPatterns(AUTH_REQUIRED_PATH_PATTERNS);
    return filterRegistrationBean;
  }

  @Bean
  public FilterRegistrationBean adminRoleFilter() {
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new RoleFilter(Role.ADMIN));
    filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
    filterRegistrationBean.addUrlPatterns(ADMIN_ROLE_REQUIRED_PATH_PATTERNS);
    return filterRegistrationBean;
  }

  @Bean
  public FilterRegistrationBean userRoleFilter() {
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new RoleFilter(Role.USER));
    filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 2);
    filterRegistrationBean.addUrlPatterns(USER_ROLE_REQUIRED_PATH_PATTERNS);
    return filterRegistrationBean;
  }
  */
}