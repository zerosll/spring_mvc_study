package com.cafe24.springmvcstudy.storage;

import com.cafe24.springmvcstudy.common.entity.BaseEntity;
import com.cafe24.springmvcstudy.member.Member;
import com.cafe24.springmvcstudy.post.Post;
import com.cafe24.springmvcstudy.post.PostVisibleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class FileInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String newName;
    private String orgName;
    private String size;
    private String ext;
    private String type;
    private String location;
    private String link;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    private Post post;

}
