package com.cafe24.springmvcstudy.storage;

import com.cafe24.springmvcstudy.post.PostDto;
import com.cafe24.springmvcstudy.post.PostRepository;
import com.cafe24.springmvcstudy.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/file")
@RequiredArgsConstructor
@Slf4j
public class FileStorageController {

    private final FileStorageService fileStorageService;

    @GetMapping("/download")
    public void download(@RequestParam(required = true) Long fileSeq, HttpServletRequest request, HttpServletResponse response) {
        try {
            fileStorageService.executeFileDownload(request, response, fileSeq);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
