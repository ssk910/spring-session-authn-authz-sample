package com.sparta.spring_session_authn_authz_sample.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginRequestDto {

  @Size(min = 1, max = 20)
  private final String username;

  @Size(min = 1, max = 20)
  private final String password;

  public LoginRequestDto(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
