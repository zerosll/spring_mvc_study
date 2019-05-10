package com.cafe24.springmvcstudy.auth;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    @Column(name = "role_name")
    private String roleName;
}