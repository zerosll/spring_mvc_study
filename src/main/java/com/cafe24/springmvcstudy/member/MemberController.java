package com.cafe24.springmvcstudy.member;

import com.cafe24.springmvcstudy.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/login")
    public String form() {
        return "";
    }

    @PostMapping("/login")
    public String login(){
        return "";
    }

    @GetMapping(path = "{seq}/posts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<Collection<Post>> postsOfMember(@PathVariable("seq") int seq, Model model) {
        Member member = memberRepository.findById((long) seq)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user seq: " + seq));

        Collection<Post> posts = member.getPosts();

        return ResponseEntity.ok(posts);
    }
}

