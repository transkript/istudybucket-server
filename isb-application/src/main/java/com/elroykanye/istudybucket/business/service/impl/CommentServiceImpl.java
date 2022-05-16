package com.elroykanye.istudybucket.business.service.impl;

import com.elroykanye.istudybucket.api.dto.PostDto;
import com.elroykanye.istudybucket.business.mapper.PostMapper;
import com.elroykanye.istudybucket.business.service.CommentService;
import com.elroykanye.istudybucket.business.service.PostService;
import com.elroykanye.istudybucket.business.service.UserService;
import com.elroykanye.istudybucket.data.entity.Post;
import com.elroykanye.istudybucket.data.entity.User;
import com.elroykanye.istudybucket.data.enums.PostType;
import com.elroykanye.istudybucket.data.repository.PostRepository;
import com.elroykanye.istudybucket.excetion.EntityException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final UserService userService;
    private final PostService postService;

    private final PostMapper postMapper;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public String addComment(PostDto postDto) {
        Post post = postService.getPost(postDto.getSourcePostId());
        User user = userService.getUser(postDto.getAuthorId());

        if(postDto.getPostId() != null && postRepository.existsById(postDto.getPostId())) {
            log.error("Adding comment: comment already exists");
            throw new EntityException.EntityAlreadyExistsException("comment", postDto.getPostId());
        }
        log.info("Adding new comment to post {}", post.getPostId());
        Post postComment = postMapper.mapDtoToPost(postDto);
        if(postDto.getPostTitle() == null) {
            postComment.setPostTitle(String.format("Reply to '%s'", post.getPostTitle()));
        }
        postComment.setSourcePost(post);
        postComment.setAuthor(user);
        postComment.setPostType(PostType.COMMENT);
        postRepository.save(postComment);
        return "Comment added successfully";
    }

    @Override
    @Transactional
    public List<PostDto> getCommentsByPost(Long postId) {
        log.info("Getting comments for post {}", postId);
        Post sourcePost = postService.getPost(postId);
        return postRepository.findAllBySourcePost(sourcePost).stream()
                .map(postMapper::mapPostToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<PostDto> getCommentsByPostAndAuthor(Long postId, Long authorId) {
        log.info("Getting comments for post {} and author {}", postId, authorId);
        return postRepository
                .findAllBySourcePostAndAuthor(postService.getPost(postId), userService.getUser(authorId))
                .stream()
                .map(postMapper::mapPostToDto).collect(Collectors.toList());
    }

    @Override
    public String updateComment(PostDto postDto) {
        Post post = postRepository.findById(postDto.getPostId()).orElseThrow(() -> {
            log.error("Updating comment: comment does not exist");
            throw new EntityException.EntityNotFoundException("comment", postDto.getPostId());
        });
        log.info("Updating comment {}", postDto.getPostId());
        post.setContent(postDto.getContent());
        postRepository.save(post);
        return "Comment updated successfully";
    }

    @Override
    public String deleteComment(Long postId) {
        // TODO authenticate this action before it is performed
        postRepository.delete(postRepository.findById(postId).orElseThrow(() -> {
            log.error("Deleting comment: comment does not exist");
            throw new EntityException.EntityNotFoundException("comment", postId);
        }));
        log.info("Deleting comment {}", postId);
        return "Comment deleted successfully";
    }


}
