package com.elroykanye.istudybucket.business.service;

import com.elroykanye.istudybucket.api.dto.PostDto;
import com.elroykanye.istudybucket.data.entity.Post;

import java.util.List;

public interface PostService {
    String addPost(PostDto postDto);

    List<PostDto> getAllPosts();

    List<PostDto> getAllPostsByAuthorId(Long authorId);

    PostDto getPostById(Long postId);

    Post getPost(Long id);
    void updatePost(Post post);
}
