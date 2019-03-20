package com.cafe24.springmvcstudy.member.repository;

import com.cafe24.springmvcstudy.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
