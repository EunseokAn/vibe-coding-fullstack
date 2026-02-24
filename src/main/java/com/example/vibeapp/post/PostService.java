package com.example.vibeapp.post;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private final PostTagRepository postTagRepository;

    public PostService(PostRepository postRepository, PostTagRepository postTagRepository) {
        this.postRepository = postRepository;
        this.postTagRepository = postTagRepository;
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

    public PostResponseDto getPostForEdit(Long id) {
        Post post = postRepository.findById(id);
        String tags = getTagsAsString(id);
        return PostResponseDto.from(post, tags);
    }

    public PostResponseDto viewPostById(Long id) {
        postRepository.incrementViews(id);
        Post post = postRepository.findById(id);
        String tags = getTagsAsString(id);
        return PostResponseDto.from(post, tags);
    }

    private String getTagsAsString(Long postNo) {
        return postTagRepository.findByPostNo(postNo).stream()
                .map(PostTag::getTagName)
                .collect(Collectors.joining(", "));
    }

    @Transactional
    public void save(PostCreateDto createDto) {
        Post post = createDto.toEntity();
        postRepository.save(post);
        saveTags(post.getId(), createDto.tags());
    }

    private void saveTags(Long postNo, String tagsString) {
        if (tagsString == null || tagsString.isBlank()) {
            return;
        }
        String[] tags = tagsString.split(",");
        for (String tagName : tags) {
            String trimmedTag = tagName.trim();
            if (!trimmedTag.isEmpty()) {
                postTagRepository.insert(new PostTag(null, postNo, trimmedTag));
            }
        }
    }

    @Transactional
    public void updatePost(Long id, PostUpdateDto updateDto) {
        Post post = postRepository.findById(id);
        if (post != null) {
            post.setTitle(updateDto.title());
            post.setContent(updateDto.content());
            post.setUpdatedAt(LocalDateTime.now());
            postRepository.save(post);

            postTagRepository.deleteByPostNo(id);
            saveTags(id, updateDto.tags());
        }
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
