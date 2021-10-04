package com.feljtech.istudybucket.business.service;

import com.feljtech.istudybucket.api.dto.PostDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    ResponseEntity<String> addPost(PostDto postDto);

    List<PostDto> getAllPosts();

    List<PostDto> getAllPostsByAuthorName(String authorName);

    ResponseEntity<?> getPostById(Long postId);
}
