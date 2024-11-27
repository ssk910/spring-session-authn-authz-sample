package com.sparta.spring_session_authn_authz_sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringSessionAuthnAuthzSampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringSessionAuthnAuthzSampleApplication.class, args);
  }

}
