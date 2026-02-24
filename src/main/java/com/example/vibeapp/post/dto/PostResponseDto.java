package com.example.vibeapp.post.dto;

import com.example.vibeapp.post.Post;
import java.time.LocalDateTime;

public record PostResponseDto(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Integer views,
        String tags) {
    public static PostResponseDto from(Post entity, String tags) {
        if (entity == null)
            return null;
        return new PostResponseDto(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getViews(),
                tags);
    }
}
