package com.sparta.spring_session_authn_authz_sample.repository;

import com.sparta.spring_session_authn_authz_sample.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> Member 엔티티를 위한 Repository. </p>
 *
 * @author Seokgyu Hwang (Chris)
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  /**
   * 입력받은 유저 이름으로 {@link Member} 엔티티를 찾아 리턴합니다.
   *
   * @param username 유저 이름
   * @return 조회한 엔티티 결과
   */
  Member findByUsername(String username);
}
