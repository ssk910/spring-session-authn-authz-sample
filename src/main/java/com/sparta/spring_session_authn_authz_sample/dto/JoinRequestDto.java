package com.sparta.spring_session_authn_authz_sample.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 회원가입 Request DTO. </p>
 *
 * @author Seokgyu Hwang (Chris)
 * @version 1.0
 * @since 1.0
 */
@Getter
public class JoinRequestDto {

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
   * 유저의 권한.
   */
  @NotBlank
  private final String role;

  /**
   * 생성자.
   *
   * @param username 유저 이름
   * @param password 비밀번호
   * @param role     유저의 권한 ({@link com.sparta.spring_session_authn_authz_sample.entity.Role})
   */
  public JoinRequestDto(String username, String password, String role) {
    this.username = username;
    this.password = password;
    this.role = role;
  }
}
