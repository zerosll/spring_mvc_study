package com.cafe24.springmvcstudy.post;

import com.cafe24.springmvcstudy.category.CategoryService;
import com.cafe24.springmvcstudy.common.annotation.IncludeCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;
    private final CategoryService categoryService;


    @GetMapping("/create")
    public String create(PostDto.Creation creation) {
        categoryService.createCategory();
        return "posts/create";
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String store(@Valid PostDto.Creation creation, BindingResult result) {
        if (result.hasErrors()) {
            return "posts/create";
        }
        postService.createPosts(creation, "test@gmail.com");

        return "redirect:/posts";
    }

    @GetMapping
    @IncludeCategory
    public String list(@RequestParam(required = false) String q, @PageableDefault(size = 5) final Pageable pageable, Model model) {

        Page<PostDto.Res> page = postRepository.search(q, pageable).map(PostDto.Res::new);
        model.addAttribute("page", page);

        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "posts/list";
    }
}
