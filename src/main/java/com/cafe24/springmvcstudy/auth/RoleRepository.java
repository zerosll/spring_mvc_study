package com.cafe24.springmvcstudy.auth;

import org.springframework.data.jpa.repository.JpaRepository;

//@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);

}