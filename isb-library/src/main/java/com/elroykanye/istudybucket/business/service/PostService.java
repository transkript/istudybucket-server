package com.elroykanye.istudybucket.business.service;

import com.elroykanye.istudybucket.api.dto.PostDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    ResponseEntity<String> addPost(PostDto postDto);

    List<PostDto> getAllPosts();

    List<PostDto> getAllPostsByAuthorId(Long authorId);

    ResponseEntity<?> getPostById(Long postId);
}
