package com.cafe24.springmvcstudy.post;

import com.cafe24.springmvcstudy.storage.FileInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class PostDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Creation {
        @NotBlank
        private String title;
        @NotEmpty
        @Length(min = 5)
        private String content;
        private PostVisibleType visibleType;
        private MultipartFile multipartFile;

        public Post toEntity() {
            return Post.builder()
                    .title(title)
                    .content(content)
                    .visibleType(visibleType)
                    .build();
        }
    }

    @Getter
    public static class Res {
        private PostVisibleType visibleType;
        private String title;
        private String content;
        private String memberEmail;
        private LocalDateTime createdAt;
        private FileInfo fileName;
        public Res(Post post) {
            this.visibleType = post.getVisibleType();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.memberEmail = post.getMember().getEmail();
            this.createdAt = post.getCreatedAt();
            this.fileName = post.getFileInfo();
        }
    }
}
