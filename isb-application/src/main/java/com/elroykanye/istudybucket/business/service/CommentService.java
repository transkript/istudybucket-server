package com.elroykanye.istudybucket.business.service;

import com.elroykanye.istudybucket.api.dto.PostDto;

import java.util.List;

public interface CommentService {
    String addComment(PostDto postDto);

    List<PostDto> getCommentsByPost(Long postId);

    List<PostDto> getCommentsByPostAndAuthor(Long postId, Long authorId);

    String updateComment(PostDto postDto);

    String deleteComment(Long postId);
}
