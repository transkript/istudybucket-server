package com.elroykanye.istudybucket.business.service.impl;

import com.elroykanye.istudybucket.api.dto.CommentDto;
import com.elroykanye.istudybucket.business.mapper.CommentMapper;
import com.elroykanye.istudybucket.business.service.CommentService;
import com.elroykanye.istudybucket.data.entity.Comment;
import com.elroykanye.istudybucket.data.entity.Post;
import com.elroykanye.istudybucket.data.entity.User;
import com.elroykanye.istudybucket.data.repository.CommentRepository;
import com.elroykanye.istudybucket.data.repository.PostRepository;
import com.elroykanye.istudybucket.data.repository.UserRepository;
import com.elroykanye.istudybucket.excetion.EntityException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public String addComment(CommentDto commentDto) {
        postRepository.findById(commentDto.getPostId())
                .ifPresentOrElse(
                        sourcePost -> {
                            log.info("Creating comment: post found");
                            userRepository.findById(commentDto.getAuthorId())
                                    .ifPresentOrElse(
                                            authorUser -> {
                                                log.info("Creating comment: user found");
                                                Comment comment = commentMapper.mapDtoToComment(commentDto);
                                                comment.setAuthor(authorUser);
                                                comment.setSourcePost(sourcePost);
                                                commentRepository.save(comment);
                                            },
                                            () -> {
                                                log.warn("Creating comment: user not found");
                                                throw new EntityException.EntityNotFoundException("user", commentDto.getAuthorId());
                                            }
                                    );
                        },
                        () -> {
                            log.warn("Creating comment: post not found");
                            throw new EntityException.EntityNotFoundException("post", commentDto.getPostId());
                        } // post not found
                );
        return "Comment added";
    }

    @Override
    @Transactional
    public List<CommentDto> getCommentsByPost(Long postId) {
        Optional<Post> sourcePostOptional = postRepository.findById(postId);
        List<CommentDto> commentDtoList = sourcePostOptional.map(post -> post
                .getComments()
                .stream()
                .map(commentMapper::mapCommentToDto)
                .collect(Collectors.toList())).orElse(null);
        if (commentDtoList == null) {
            log.warn("Getting comments: post not found");
            throw new EntityException.EntityNotFoundException("post", postId);
        } else {
            log.info("Getting comments: post found");
            return commentDtoList;
        }
    }

    @Override
    @Transactional
    public List<CommentDto> getCommentsByPostAndAuthor(Long postId, Long authorId) {
        Optional<Post> sourcePostOptional = postRepository.findById(postId);

        if (sourcePostOptional.isPresent()) {
            Optional<User> authorUser = userRepository.findById(authorId);
            List<CommentDto> commentDtoList = authorUser.map(user -> commentRepository.findAll().stream()
                    .filter(comment -> comment.getSourcePost().equals(sourcePostOptional.get()) && comment.getAuthor().equals(user))
                    .map(commentMapper::mapCommentToDto)
                    .collect(Collectors.toList())).orElse(null);
            if(commentDtoList != null) {
                log.info("Getting comments: user found");
                return commentDtoList;
            } else {
                log.warn("Getting comments: user not found");
                throw new EntityException.EntityNotFoundException("user", authorId);
            }
        }
        log.warn("Getting comments: post or author not found");
        throw new EntityException.EntityNotFoundException("post", postId);
    }


}
