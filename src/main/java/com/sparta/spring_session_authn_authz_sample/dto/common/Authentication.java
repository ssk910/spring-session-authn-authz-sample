package com.sparta.spring_session_authn_authz_sample.dto.common;

import com.sparta.spring_session_authn_authz_sample.entity.Role;
import lombok.Getter;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 유저 인증 정보. </p>
 *
 * @author Seokgyu Hwang (Chris)
 * @version 1.0
 * @since 1.0
 */
@Getter
public class Authentication {

  /**
   * 유저 이름.
   */
  private final String username;

  /**
   * 유저의 권한.
   */
  private final Role role;

  /**
   * 생성자.
   *
   * @param username 유저 이름
   * @param role     권한
   */
  public Authentication(String username, Role role) {
    this.username = username;
    this.role = role;
  }
}
