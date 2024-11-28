package com.sparta.spring_session_authn_authz_sample.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 멤버 엔티티. </p>
 *
 * @author Seokgyu Hwang (Chris)
 * @version 1.0
 * @since 1.0
 */
@Getter
@Entity
@Table(name = "member")
public class Member extends BaseEntity {

  /**
   * ID.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 유저 이름.
   */
  @Column(unique = true, length = 20)
  private String username;

  /**
   * <p>비밀번호.</p>
   * <b>NOTE:</b> 예제의 단순화를 위해 암호화하지 않았습니다.
   */
  @Column(length = 20)
  private String password;

  /**
   * 유저의 권한. 기본값은 {@code Role.USER}입니다.
   */
  @Enumerated(value = EnumType.STRING)
  private Role role = Role.USER;

  /**
   * 기본 생성자.
   */
  public Member() {
  }

  /**
   * 생성자.
   *
   * @param username 유저 이름
   * @param password 비밀번호
   */
  public Member(String username, String password) {
    this.username = username;
    this.password = password;
  }

  /**
   * 생성자.
   *
   * @param username 유저 이름
   * @param password 비밀번호
   * @param role     유저의 권한
   */
  public Member(String username, String password, Role role) {
    this.username = username;
    this.password = password;
    this.role = role;
  }
}
