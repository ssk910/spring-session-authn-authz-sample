package com.sparta.spring_session_authn_authz_sample.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class JoinRequestDto {

  @Size(min = 1, max = 20)
  private final String username;

  @Size(min = 1, max = 20)
  private final String password;

  @NotBlank
  private final String role;

  public JoinRequestDto(String username, String password, String role) {
    this.username = username;
    this.password = password;
    this.role = role;
  }
}
