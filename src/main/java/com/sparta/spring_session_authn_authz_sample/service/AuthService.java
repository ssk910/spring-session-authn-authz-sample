package com.sparta.spring_session_authn_authz_sample.service;

import com.sparta.spring_session_authn_authz_sample.dto.JoinRequestDto;
import com.sparta.spring_session_authn_authz_sample.dto.LoginRequestDto;
import com.sparta.spring_session_authn_authz_sample.dto.common.Authentication;
import com.sparta.spring_session_authn_authz_sample.entity.Member;
import com.sparta.spring_session_authn_authz_sample.entity.Role;
import com.sparta.spring_session_authn_authz_sample.exception.UnauthorizedException;
import com.sparta.spring_session_authn_authz_sample.repository.MemberRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 회원가입 및 로그인 관련 서비스. </p>
 *
 * @author Seokgyu Hwang (Chris)
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class AuthService {

  /**
   * Repository.
   */
  private final MemberRepository memberRepository;

  /**
   * 회원가입.
   *
   * @param requestDto {@link JoinRequestDto} 객체
   */
  @Transactional
  public void join(JoinRequestDto requestDto) {
    Member member = new Member(
        requestDto.getUsername(),
        requestDto.getPassword(),
        Role.of(requestDto.getRole())
    );

    this.memberRepository.save(member);
  }

  /**
   * 입력 받은 정보와 DB의 정보가 일치하면 {@code Authentication} 객체를 생성하여 리턴합니다.
   *
   * @param requestDto {@link LoginRequestDto} 객체
   * @return {@link Authentication} 객체
   * @throws UnauthorizedException 로그인 정보가 일치하지 않는 경우
   */
  public Authentication login(LoginRequestDto requestDto) throws UnauthorizedException {
    Member member = memberRepository.findByUsername(requestDto.getUsername());

    if (member == null) {
      throw new UnauthorizedException(HttpStatus.UNAUTHORIZED, "유효하지 않은 사용자입니다.");
    } else if (!Objects.equals(member.getPassword(), requestDto.getPassword())) {
      throw new UnauthorizedException(HttpStatus.UNAUTHORIZED, "올바르지 않은 비밀번호입니다.");
    }

    return new Authentication(member.getUsername(), member.getRole());
  }
}
