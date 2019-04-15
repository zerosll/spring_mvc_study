package com.cafe24.springmvcstudy.post;

import com.cafe24.springmvcstudy.common.exception.NotFoundException;
import com.cafe24.springmvcstudy.member.Member;
import com.cafe24.springmvcstudy.member.MemberRepository;
import com.cafe24.springmvcstudy.storage.FileInfo;
import com.cafe24.springmvcstudy.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor

public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final FileStorageService fileStorageService;

    @Transactional
    public Post createPosts(PostDto.Creation creation, String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("%s not found", email)));

        MultipartFile multipartFile = creation.getMultipartFile();


        Post post = creation.toEntity();
        if(!multipartFile.isEmpty()){
            String newFileName = fileStorageService.saveFileToLocal(creation.getMultipartFile());
            FileInfo fileInfo = fileStorageService.saveFileInfoToDB(creation.getMultipartFile(), newFileName);
            post.setFileInfo(fileInfo);
            post.getFileInfo().setPost(post);

        }
        post.setMember(member);


//        MultipartFile multipartFile = creation.getMultipartFile();
//        fileStorageService.saveFileToLocal(creation.getMultipartFile());
//
//        Post post = creation.toEntity();
//        post.setMember(member);
//        Post savedPost = postRepository.save(post);
//
//        FileInfo fileInfo = fileStorageService.saveFileInfoToDB(creation.getMultipartFile());
//        fileInfo.setPost(savedPost);
//        fileInfoRepository.save(fileInfo);

        return postRepository.save(post);
    }

    public void executeFileDownload(HttpServletRequest request, HttpServletResponse response, Long fileSeq) throws Exception {
        fileStorageService.executeFileDownload(request, response, fileSeq);
    }
}
