package com.cafe24.springmvcstudy;

import com.cafe24.springmvcstudy.auth.Account;
import com.cafe24.springmvcstudy.auth.CustomUserDetailService;
import com.cafe24.springmvcstudy.member.Member;
import com.cafe24.springmvcstudy.member.MemberRepository;
import com.cafe24.springmvcstudy.post.Post;
import com.cafe24.springmvcstudy.post.PostRepository;
import com.cafe24.springmvcstudy.post.PostVisibleType;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.LongStream;

@Component
public class StartUpInit  implements ApplicationRunner {


    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CustomUserDetailService customUserDetailService;
    private final PasswordEncoder passwordEncoder;

    public StartUpInit(MemberRepository memberRepository, PostRepository postRepository, CustomUserDetailService customUserDetailService, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
        this.customUserDetailService = customUserDetailService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Member member = Member.builder().email("test@gmail.com").name("test").password("test").build();
        Member savedMember = memberRepository.save(member);

        LongStream.range(1, 2)
                .mapToObj(value -> {
                    return Post.builder().title("test" + value).content("content_" + value).visibleType(PostVisibleType.PRIVATE).build();
                })
                .map(post -> {
                    post.setMember(savedMember);
                    return postRepository.save(post);
                })
                .forEach(System.out::println);

        Account silverhyuk = customUserDetailService.createAccount("eunhyuk", passwordEncoder.encode("1234"));
    }
}

