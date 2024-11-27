package com.sparta.spring_session_authn_authz_sample.service;

import com.sparta.spring_session_authn_authz_sample.dto.Authentication;
import com.sparta.spring_session_authn_authz_sample.dto.JoinRequestDto;
import com.sparta.spring_session_authn_authz_sample.dto.LoginRequestDto;
import com.sparta.spring_session_authn_authz_sample.entity.Member;
import com.sparta.spring_session_authn_authz_sample.entity.Role;
import com.sparta.spring_session_authn_authz_sample.repository.MemberRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final MemberRepository memberRepository;

  @Transactional
  public void join(JoinRequestDto requestDto) {
    Member member = new Member(
        requestDto.getUsername(),
        requestDto.getPassword(),
        Role.of(requestDto.getRole())
    );

    this.memberRepository.save(member);
  }

  public Authentication login(LoginRequestDto requestDto) {
    Member member = memberRepository.findByUsername(requestDto.getUsername());

    if (member == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유효하지 않은 사용자입니다.");
    } else if (!Objects.equals(member.getPassword(), requestDto.getPassword())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "올바르지 않은 비밀번호입니다.");
    }

    return new Authentication(member.getUsername(), member.getRole());
  }
}
