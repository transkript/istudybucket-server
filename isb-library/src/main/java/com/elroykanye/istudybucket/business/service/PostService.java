package com.elroykanye.istudybucket.business.service;

import com.elroykanye.istudybucket.api.dto.PostDto;

import java.util.List;

public interface PostService {
    String addPost(PostDto postDto);

    List<PostDto> getAllPosts();

    List<PostDto> getAllPostsByAuthorId(Long authorId);

    PostDto getPostById(Long postId);
}
