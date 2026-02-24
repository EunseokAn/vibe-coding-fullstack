package com.example.vibeapp.post;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface PostMapper {
    List<Post> findPaged(@Param("offset") int offset, @Param("limit") int limit);
    long count();
    Post findById(Long id);
    void insert(Post post);
    void update(Post post);
    void deleteById(Long id);
    void incrementViews(Long id);
}
