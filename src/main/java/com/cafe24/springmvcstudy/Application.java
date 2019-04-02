package com.cafe24.springmvcstudy;

import com.cafe24.springmvcstudy.member.Member;
import com.cafe24.springmvcstudy.member.MemberRepository;
import com.cafe24.springmvcstudy.post.Post;
import com.cafe24.springmvcstudy.post.PostRepository;
import com.cafe24.springmvcstudy.post.PostVisibleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.stream.LongStream;

@SpringBootApplication
@Slf4j
public class Application {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PostRepository postRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            Member member = Member.builder().email("test@gmail.com").name("test").password("test").build();
            Member savedMember = memberRepository.save(member);

            LongStream.range(1, 11)
                    .mapToObj(value -> {
                        return Post.builder().title("test" + value).content("content_" + value).visibleType(PostVisibleType.PRIVATE).build();
                    })
                    .map(post -> {
                        post.setMember(savedMember);
                        return postRepository.save(post);
                    })
                    .forEach(System.out::println);
        };
    }
}

