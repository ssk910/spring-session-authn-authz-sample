package com.sparta.spring_session_authn_authz_sample.service;

import com.sparta.spring_session_authn_authz_sample.dto.MemberResponseDto;
import com.sparta.spring_session_authn_authz_sample.entity.Member;
import com.sparta.spring_session_authn_authz_sample.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 관리자 권한 서비스. </p>
 *
 * @author Seokgyu Hwang (Chris)
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

  /**
   * Repository.
   */
  private final MemberRepository memberRepository;

  /**
   * 관리자 권한에 대한 로직을 수행.
   *
   * @param username 유저 이름
   * @return {@link MemberResponseDto} 객체
   */
  public MemberResponseDto performLogic(String username) {
    Member member = this.memberRepository.findByUsername(username);

    log.info("admin 권한이 필요한 로직 수행.");

    return new MemberResponseDto(member);
  }
}
