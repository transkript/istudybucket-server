package com.elroykanye.istudybucket.api.controller;

import com.elroykanye.istudybucket.api.dto.CommentDto;
import com.elroykanye.istudybucket.business.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/comment")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addComment(@RequestBody CommentDto commentDto) {
        log.info("adding comment to post: {}", commentDto.getPostId());
        return new ResponseEntity<>(commentService.addComment(commentDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPost(@PathVariable(value = "postId") Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }

    @GetMapping(value = "/{postId}/{authorId}")
    public ResponseEntity<List<CommentDto>> getCommentByPostAndAuthor(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "authorId") Long authorId) {
        return ResponseEntity.ok(commentService.getCommentsByPostAndAuthor(postId, authorId));
    }
}
