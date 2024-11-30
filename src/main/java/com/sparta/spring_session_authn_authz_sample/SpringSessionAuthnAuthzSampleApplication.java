package com.sparta.spring_session_authn_authz_sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 메인 클래스.
 */
@EnableJpaAuditing
@SpringBootApplication
public class SpringSessionAuthnAuthzSampleApplication {

  /**
   * 메인.
   *
   * @param args args
   */
  public static void main(String[] args) {
    SpringApplication.run(SpringSessionAuthnAuthzSampleApplication.class, args);
  }

}
