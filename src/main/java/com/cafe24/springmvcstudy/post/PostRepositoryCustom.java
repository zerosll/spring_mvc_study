package com.cafe24.springmvcstudy.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {

    public Page<Post> search(String q, Pageable pageable);

    public List<Post> findByTitle(String title);

    public List<Post> findAllWithMember();
}
