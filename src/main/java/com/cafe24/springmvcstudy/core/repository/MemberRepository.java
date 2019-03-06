package com.cafe24.springmvcstudy.core.repository;

import com.cafe24.springmvcstudy.core.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
