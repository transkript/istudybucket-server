package com.elroykanye.istudybucket.business.service;

import com.elroykanye.istudybucket.api.dto.CommentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {
    ResponseEntity<String> addComment(CommentDto commentDto);

    ResponseEntity<List<CommentDto>> getCommentsByPost(Long postId);

    ResponseEntity<List<CommentDto>> getCommentsByPostAndAuthor(Long postId, Long authorId);
}
