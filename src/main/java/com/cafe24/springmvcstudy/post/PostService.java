package com.cafe24.springmvcstudy.post;

import com.cafe24.springmvcstudy.common.exception.NotFoundException;
import com.cafe24.springmvcstudy.common.util.FileUploadUtil;
import com.cafe24.springmvcstudy.member.Member;
import com.cafe24.springmvcstudy.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final FileUploadUtil fileUploadUtil;

    public Post createPosts(PostDto.Creation creation, String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("%s not found", email)));

        MultipartFile multipartFile = creation.getMultipartFile();
        Long fileSeq = fileUploadUtil.saveFile(multipartFile);


        Post post = creation.toEntity();
        //post.setFileSeq(fileSeq);
        post.setMember(member);
        return postRepository.save(post);
    }
}
