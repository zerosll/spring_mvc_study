package com.cafe24.springmvcstudy.post;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @After
    public void tearDown() {
        postRepository.deleteAllInBatch();
    }

    @Test
    public void 게시글저장_불러오기() {
        Post save = postRepository.save(Post.builder()
                .title("테스트 제목")
                .content("테스트 내용")
                .build());

        save.setTitle("ttttt");
        postRepository.save(save);

        List<Post> list = postRepository.findAll();

        assertThat(list.size()).isEqualTo(5);
    }
}