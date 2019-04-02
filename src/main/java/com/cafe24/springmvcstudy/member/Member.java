package com.cafe24.springmvcstudy.member;

import com.cafe24.springmvcstudy.common.entity.BaseEntity;
import com.cafe24.springmvcstudy.post.Post;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String email;
    private String name;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private Collection<Post> posts;
}
