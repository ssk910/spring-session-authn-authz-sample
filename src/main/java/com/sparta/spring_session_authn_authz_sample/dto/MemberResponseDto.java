package com.sparta.spring_session_authn_authz_sample.dto;

import com.sparta.spring_session_authn_authz_sample.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 회원 정보 Response DTO. </p>
 *
 * @author Seokgyu Hwang (Chris)
 * @version 1.0
 * @since 1.0
 */
@Getter
@RequiredArgsConstructor
public class MemberResponseDto {

  /**
   * 유저 이름.
   */
  private final String username;

  /**
   * 비밀번호.
   */
  private final String role;

  /**
   * 생성자.
   *
   * @param member {@link Member} 엔티티
   */
  public MemberResponseDto(Member member) {
    this.username = member.getUsername();
    this.role = member.getRole().getName();
  }
}
