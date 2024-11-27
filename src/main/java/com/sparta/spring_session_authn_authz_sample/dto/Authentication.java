package com.sparta.spring_session_authn_authz_sample.dto;

import com.sparta.spring_session_authn_authz_sample.entity.Role;
import lombok.Getter;

@Getter
public class Authentication {

  private final String username;
  private final Role role;

  public Authentication(String username, Role role) {
    this.username = username;
    this.role = role;
  }
}
