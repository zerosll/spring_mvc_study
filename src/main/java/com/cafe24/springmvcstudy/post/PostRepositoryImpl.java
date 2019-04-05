package com.cafe24.springmvcstudy.post;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.cafe24.springmvcstudy.member.QMember.member;
import static com.cafe24.springmvcstudy.post.QPost.post;
import static com.cafe24.springmvcstudy.storage.QFileInfo.fileInfo;

public class PostRepositoryImpl extends QuerydslRepositorySupport implements PostRepositoryCustom {

    public PostRepositoryImpl() {
        super(Post.class);
    }

    public Page<Post> search(String q, Pageable pageable) {
        JPQLQuery<Post> query = from(post)
                .where(post.createdAt.goe(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS)));  // 오늘 등록된 글

        if (q != null) {
            query.where(post.content.likeIgnoreCase(q + "%"));
        }
        query.leftJoin(post.fileInfo, fileInfo).fetchJoin();

        List<Post> list = getQuerydsl().applyPagination(pageable, query).fetch();

        return new PageImpl<>(list, pageable, query.fetchCount());
    }

    public List<Post> findByTitle(String title) {
        return from(post)
                .join(post.member, member).fetchJoin()
                .where(post.title.eq(title))
                .fetch();
    }

    @Override
    public List<Post> findAllWithMember() {
        return from(post)
                .join(post.member, member).fetchJoin()
                .fetch();
    }
}
