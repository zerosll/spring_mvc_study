package com.cafe24.springmvcstudy.post;

import com.cafe24.springmvcstudy.common.exception.NotFoundException;
import com.cafe24.springmvcstudy.common.util.FileUploadUtil;
import com.cafe24.springmvcstudy.member.Member;
import com.cafe24.springmvcstudy.member.MemberRepository;
import com.cafe24.springmvcstudy.storage.FileInfo;
import com.cafe24.springmvcstudy.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final FileStorageService fileStorageService;

    public Post createPosts(PostDto.Creation creation, String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("%s not found", email)));

        MultipartFile multipartFile = creation.getMultipartFile();
        fileStorageService.saveFileToLocal(creation.getMultipartFile());
        FileInfo fileInfo = fileStorageService.saveFileInfoToDB(creation.getMultipartFile());

        Post post = creation.toEntity();
        post.setFileInfo(fileInfo);
        post.setMember(member);
        return postRepository.save(post);
    }
}
