package com.example.vibeapp.post;

import org.springframework.stereotype.Service;
import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostResponseDto;
import com.example.vibeapp.post.dto.PostUpdateDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostListDto> findAllPaged(int page, int size) {
        int offset = (page - 1) * size;
        return postRepository.findPaged(offset, size).stream()
                .map(PostListDto::from)
                .collect(Collectors.toList());
    }

    public int getTotalPages(int size) {
        long totalElements = postRepository.count();
        return (int) Math.ceil((double) totalElements / size);
    }

    public PostResponseDto findById(Long id) {
        postRepository.incrementViews(id);
        Post post = postRepository.findById(id);
        return PostResponseDto.from(post);
    }

    public void save(PostCreateDto createDto) {
        Post post = createDto.toEntity();
        postRepository.save(post);
    }

    public void updatePost(Long id, PostUpdateDto updateDto) {
        Post post = postRepository.findById(id);
        if (post != null) {
            post.setTitle(updateDto.getTitle());
            post.setContent(updateDto.getContent());
            post.setUpdatedAt(LocalDateTime.now());
        }
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
