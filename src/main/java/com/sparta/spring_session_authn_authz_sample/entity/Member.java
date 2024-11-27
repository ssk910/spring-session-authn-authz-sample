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

@Getter
@Entity
@Table(name = "member")
public class Member extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, length = 20)
  private String username;

  @Column(length = 20)
  private String password;

  @Enumerated(value = EnumType.STRING)
  private Role role = Role.USER;

  public Member() {
  }

  public Member(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public Member(String username, String password, Role role) {
    this.username = username;
    this.password = password;
    this.role = role;
  }
}
