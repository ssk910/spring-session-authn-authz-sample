package com.sparta.spring_session_authn_authz_sample.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 로그인 Request DTO. </p>
 *
 * @author Seokgyu Hwang (Chris)
 * @version 1.0
 * @since 1.0
 */
@Getter
public class LoginRequestDto {

  /**
   * 유저 이름.
   */
  @Size(min = 1, max = 20)
  private final String username;

  /**
   * 비밀번호.
   */
  @Size(min = 1, max = 20)
  private final String password;

  /**
   * 생성자.
   *
   * @param username 유저 이름
   * @param password 비밀번호
   */
  public LoginRequestDto(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
