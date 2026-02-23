package com.example.vibeapp;

import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {
    private final List<Post> posts = new ArrayList<>();

    public PostRepository() {
        // Seed data: 10 sample posts
        for (long i = 1; i <= 10; i++) {
            posts.add(new Post(
                    i,
                    "테스트 게시글 제목 " + i,
                    "이것은 " + i + "번째 게시글의 내용입니다.",
                    LocalDateTime.now().minusDays(10 - i),
                    LocalDateTime.now().minusDays(10 - i),
                    (int) (Math.random() * 100)));
        }
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts);
    }

    public Post findByNo(Long no) {
        return posts.stream()
                .filter(p -> p.getNo().equals(no))
                .findFirst()
                .orElse(null);
    }

    public void incrementViews(Long no) {
        Post post = findByNo(no);
        if (post != null) {
            post.setViews(post.getViews() + 1);
        }
    }

    public void save(Post post) {
        long nextId = posts.stream()
                .mapToLong(Post::getNo)
                .max()
                .orElse(0L) + 1;
        post.setNo(nextId);
        posts.add(post);
    }
}
