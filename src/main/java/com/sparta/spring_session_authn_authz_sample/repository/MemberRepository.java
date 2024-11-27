package com.sparta.spring_session_authn_authz_sample.repository;

import com.sparta.spring_session_authn_authz_sample.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  Member findByUsername(String username);
}
