package com.example.vibeapp.post;

import org.springframework.stereotype.Repository;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PostRepository {
    private final PostMapper postMapper;

    public PostRepository(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @jakarta.annotation.PostConstruct
    public void init() {
        if (postMapper.count() == 0) {
            for (long i = 1; i <= 10; i++) {
                Post post = new Post();
                post.setTitle("테스트 게시글 제목 " + i);
                post.setContent("이것은 " + i + "번째 게시글의 내용입니다.");
                post.setCreatedAt(LocalDateTime.now().minusDays(10 - i));
                post.setUpdatedAt(LocalDateTime.now().minusDays(10 - i));
                post.setViews((int) (java.lang.Math.random() * 100));
                postMapper.insert(post);
            }
        }
    }

    public List<Post> findPaged(int offset, int limit) {
        return postMapper.findPaged(offset, limit);
    }

    public long count() {
        return postMapper.count();
    }

    public Post findById(Long id) {
        return postMapper.findById(id);
    }

    public void incrementViews(Long id) {
        postMapper.incrementViews(id);
    }

    public void save(Post post) {
        if (post.getId() == null) {
            post.setCreatedAt(LocalDateTime.now());
            post.setUpdatedAt(LocalDateTime.now());
            post.setViews(0);
            postMapper.insert(post);
        } else {
            post.setUpdatedAt(LocalDateTime.now());
            postMapper.update(post);
        }
    }

    public void deleteById(Long id) {
        postMapper.deleteById(id);
    }
}
