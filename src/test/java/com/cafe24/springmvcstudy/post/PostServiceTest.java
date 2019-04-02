package com.cafe24.springmvcstudy.post;

import com.cafe24.springmvcstudy.member.Member;
import com.cafe24.springmvcstudy.member.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @Test
    public void createPosts() {
//        memberRepository.save(Member.builder().email("test@test.com").build());

//        PostDto.Creation creation = new PostDto.Creation("Test", "Test");
//        postService.createPosts(creation, "test@test.com");

//        List<Post> postsList = postRepository.findAllWithMember();
//
//        Assert.assertEquals(postsList.size(), 1);
    }
}