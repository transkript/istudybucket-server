package com.elroykanye.istudybucket.api.controller;

import com.elroykanye.istudybucket.api.dto.PostDto;
import com.elroykanye.istudybucket.business.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@AllArgsConstructor
@RequestMapping("/api/post")
public class PostController {
   private final PostService postService;

   @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<String> addPost(@RequestBody PostDto postDto) {
       log.info("Adding post: {}", postDto);
       return ResponseEntity.ok(postService.addPost(postDto));
   }

   @GetMapping(value = "/all")
   public ResponseEntity<List<PostDto>> getAllPosts() {
       log.info("Getting all posts");
       return ResponseEntity.ok(postService.getAllPosts());
   }

   @GetMapping(value = "/{authorId}")
   public ResponseEntity<List<PostDto>> getAllPostsByAuthorId(@PathVariable Long authorId){
       log.info("Getting all posts by authorId: {}", authorId);
       return ResponseEntity.ok(postService.getAllPostsByAuthorId(authorId));
   }

   @GetMapping(value = "/{postId}")
   public ResponseEntity<PostDto> getPostById(@PathVariable Long postId){
       log.info("Getting post by id: {}", postId);
       return ResponseEntity.ok(postService.getPostById(postId));
   }
}
