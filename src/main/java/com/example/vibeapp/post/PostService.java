package com.example.vibeapp.post;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAllPaged(int page, int size) {
        int offset = (page - 1) * size;
        return postRepository.findPaged(offset, size);
    }

    public int getTotalPages(int size) {
        long totalElements = postRepository.count();
        return (int) Math.ceil((double) totalElements / size);
    }

    public Post findById(Long id) {
        postRepository.incrementViews(id);
        return postRepository.findById(id);
    }

    public void save(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(null);
        post.setViews(0);
        postRepository.save(post);
    }

    public void updatePost(Long id, String title, String content) {
        Post post = postRepository.findById(id);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
            post.setUpdatedAt(LocalDateTime.now());
        }
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
