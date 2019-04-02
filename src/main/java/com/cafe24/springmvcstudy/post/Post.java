package com.cafe24.springmvcstudy.post;

import com.cafe24.springmvcstudy.common.entity.BaseEntity;
import com.cafe24.springmvcstudy.member.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String title;
    private String content;
    @Enumerated(value = EnumType.STRING)
    private PostVisibleType visibleType;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private Member member;
}

