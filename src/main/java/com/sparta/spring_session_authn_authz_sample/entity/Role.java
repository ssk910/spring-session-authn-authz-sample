package com.sparta.spring_session_authn_authz_sample.entity;

import lombok.Getter;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 사용자의 권한을 나타내는 Enum 클래스. </p>
 *
 * @author Seokgyu Hwang (Chris)
 * @version 1.0
 * @since 1.0
 */
@Getter
public enum Role {
  /**
   * user 권한.
   */
  USER("user"),
  /**
   * admin 권한.
   */
  ADMIN("admin");

  /**
   * 권한 이름.
   */
  private final String name;

  /**
   * 생성자.
   *
   * @param name 권한 이름.
   */
  Role(String name) {
    this.name = name;
  }

  /**
   * 입력받은 값에 해당하는 {@link Role}을 찾아 리턴합니다.
   *
   * @param roleName 권한 이름.
   * @return {@link Role}
   * @throws IllegalArgumentException 입력받은 값에 해당하는 권한을 찾을 수 없는 경우
   */
  public static Role of(String roleName) throws IllegalArgumentException {
    for (Role role : values()) {
      if (role.getName().equals(roleName)) {
        return role;
      }
    }

    throw new IllegalArgumentException("해당하는 이름의 권한을 찾을 수 없습니다: " + roleName);
  }
}
