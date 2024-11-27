package com.sparta.spring_session_authn_authz_sample.service;

import com.sparta.spring_session_authn_authz_sample.dto.MemberResponseDto;
import com.sparta.spring_session_authn_authz_sample.entity.Member;
import com.sparta.spring_session_authn_authz_sample.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final MemberRepository memberRepository;

  public MemberResponseDto performLogic(String username) {
    Member member = this.memberRepository.findByUsername(username);

    log.info("user 권한이 필요한 로직 수행.");

    return new MemberResponseDto(member);
  }
}
