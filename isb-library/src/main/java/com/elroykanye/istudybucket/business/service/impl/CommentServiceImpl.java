package com.elroykanye.istudybucket.business.service.impl;

import com.elroykanye.istudybucket.api.dto.CommentDto;
import com.elroykanye.istudybucket.business.mapper.CommentMapper;
import com.elroykanye.istudybucket.business.service.CommentService;
import com.elroykanye.istudybucket.business.service.PostService;
import com.elroykanye.istudybucket.business.service.UserService;
import com.elroykanye.istudybucket.data.entity.Comment;
import com.elroykanye.istudybucket.data.entity.Post;
import com.elroykanye.istudybucket.data.entity.User;
import com.elroykanye.istudybucket.data.repository.CommentRepository;
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
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    @Override
    @Transactional
    public String addComment(CommentDto commentDto) {
        Post post = postService.getPost(commentDto.getPostId());
        User user = userService.getUser(commentDto.getAuthorId());

        if(commentDto.getCommentId() != null && commentRepository.existsById(commentDto.getCommentId())) {
            log.error("Adding comment: comment already exists");
            throw new EntityException.EntityAlreadyExistsException("comment", commentDto.getCommentId());
        }
        log.info("Adding new comment to post {}", post.getPostId());
        Comment comment = commentMapper.mapDtoToComment(commentDto);
        comment.setSourcePost(post);
        comment.setAuthor(user);
        commentRepository.save(comment);
        return "Comment added successfully";
    }

    @Override
    @Transactional
    public List<CommentDto> getCommentsByPost(Long postId) {
        log.info("Getting comments for post {}", postId);
        Post sourcePost = postService.getPost(postId);
        return commentRepository.findAll().stream()
                .filter(comment -> comment.getSourcePost().equals(sourcePost))
                .map(commentMapper::mapCommentToDto).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public List<CommentDto> getCommentsByPostAndAuthor(Long postId, Long authorId) {
        log.info("Getting comments for post {} and author {}", postId, authorId);

        return commentRepository.findAll().stream()
                .filter(comment -> comment.getSourcePost().getPostId().equals(postService.getPost(postId).getPostId())
                        && comment.getAuthor().getUserId().equals(authorId))
                .map(commentMapper::mapCommentToDto).collect(Collectors.toList());
    }

    @Override
    public String updateComment(CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentDto.getCommentId()).orElseThrow(() -> {
            log.error("Updating comment: comment does not exist");
            throw new EntityException.EntityNotFoundException("comment", commentDto.getCommentId());
        });
        log.info("Updating comment {}", commentDto.getCommentId());
        comment.setContent(commentDto.getContent());
        commentRepository.save(comment);
        return "Comment updated successfully";
    }

    @Override
    public String deleteComment(Long commentId) {
        commentRepository.delete(commentRepository.findById(commentId).orElseThrow(() -> {
            log.error("Deleting comment: comment does not exist");
            throw new EntityException.EntityNotFoundException("comment", commentId);
        }));
        log.info("Deleting comment {}", commentId);
        return "Comment deleted successfully";
    }


}
