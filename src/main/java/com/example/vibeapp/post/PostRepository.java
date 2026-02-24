package com.example.vibeapp.post;

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

    public List<Post> findPaged(int offset, int limit) {
        int total = posts.size();
        if (offset >= total) {
            return new ArrayList<>();
        }
        int toIndex = Math.min(offset + limit, total);
        return new ArrayList<>(posts.subList(offset, toIndex));
    }

    public long count() {
        return posts.size();
    }

    public Post findById(Long id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void incrementViews(Long id) {
        Post post = findById(id);
        if (post != null) {
            post.setViews(post.getViews() + 1);
        }
    }

    public void save(Post post) {
        long nextId = posts.stream()
                .mapToLong(Post::getId)
                .max()
                .orElse(0L) + 1;
        post.setId(nextId);
        posts.add(post);
    }

    public void deleteById(Long id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
