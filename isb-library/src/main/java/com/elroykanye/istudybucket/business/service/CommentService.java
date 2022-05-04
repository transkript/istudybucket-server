package com.elroykanye.istudybucket.business.service;

import com.elroykanye.istudybucket.api.dto.CommentDto;

import java.util.List;

public interface CommentService {
    String addComment(CommentDto commentDto);

    List<CommentDto> getCommentsByPost(Long postId);

    List<CommentDto> getCommentsByPostAndAuthor(Long postId, Long authorId);

    String updateComment(CommentDto commentDto);

    String deleteComment(Long commentId);
}
