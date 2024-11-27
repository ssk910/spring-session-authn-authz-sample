package com.sparta.spring_session_authn_authz_sample.dto;

import com.sparta.spring_session_authn_authz_sample.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberResponseDto {
  private final String username;
  private final String role;

  public MemberResponseDto(Member member) {
    this.username = member.getUsername();
    this.role = member.getRole().getName();
  }
}
