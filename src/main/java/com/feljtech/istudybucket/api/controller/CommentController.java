package com.feljtech.istudybucket.api.controller;

import com.feljtech.istudybucket.api.dto.CommentDto;
import com.feljtech.istudybucket.business.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/comment")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addComment(@RequestBody CommentDto commentDto) {
        return commentService.addComment(commentDto);
    }

    @GetMapping(value = "/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPost(@PathVariable(value = "postId") Long postId) {
        return commentService.getCommentsByPost(postId);
    }

    @GetMapping(value = "/{postId}/{authorId}")
    public ResponseEntity<List<CommentDto>> getCommentByPostAndAuthor(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "authorId") Long authorId) {
        return commentService.getCommentsByPostAndAuthor(postId, authorId);
    }
}
