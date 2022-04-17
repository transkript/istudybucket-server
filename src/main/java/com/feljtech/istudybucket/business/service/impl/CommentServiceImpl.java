package com.feljtech.istudybucket.business.service.impl;

import com.feljtech.istudybucket.api.dto.CommentDto;
import com.feljtech.istudybucket.data.entity.Comment;
import com.feljtech.istudybucket.data.entity.Post;
import com.feljtech.istudybucket.data.entity.User;
import com.feljtech.istudybucket.business.mapper.CommentMapper;
import com.feljtech.istudybucket.data.repository.CommentRepository;
import com.feljtech.istudybucket.data.repository.PostRepository;
import com.feljtech.istudybucket.data.repository.UserRepository;
import com.feljtech.istudybucket.business.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public ResponseEntity<String> addComment(CommentDto commentDto) {
        AtomicBoolean commentAdded = new AtomicBoolean(false);
        postRepository.findById(commentDto.getPostId())
                .ifPresentOrElse( // if post exists
                        sourcePost -> userRepository.findById(commentDto.getAuthorId())
                                .ifPresentOrElse(
                                        authorUser -> {
                                            Comment comment = commentMapper.mapDtoToComment(commentDto);
                                            comment.setAuthor(authorUser);
                                            comment.setSourcePost(sourcePost);
                                            commentRepository.save(comment);

                                            // comment added
                                            commentAdded.set(true);
                                        },
                                        () -> commentAdded.set(false) // author not found
                                ),
                        () -> commentAdded.set(false) // post not found
                );
        return commentAdded.get() ?
                new ResponseEntity<>("Comment added", HttpStatus.CREATED):
                new ResponseEntity<>("Comment not added", HttpStatus.FORBIDDEN);
    }

    @Override
    @Transactional
    public ResponseEntity<List<CommentDto>> getCommentsByPost(Long postId) {
        Optional<Post> sourcePostOptional = postRepository.findById(postId);
        if (sourcePostOptional.isPresent()) {
            List<CommentDto> commentDtoList = sourcePostOptional.get()
                    .getComments()
                    .stream()
                    .map(commentMapper::mapCommentToDto)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
        }
        return null;
        // TODO add exceptions
    }

    @Override
    @Transactional
    public ResponseEntity<List<CommentDto>> getCommentsByPostAndAuthor(Long postId, Long authorId) {
        Optional<Post> sourcePostOptional = postRepository.findById(postId);
        if (sourcePostOptional.isPresent()) {
            Optional<User> authorUser = userRepository.findById(authorId);
            if(authorUser.isPresent()) {
                List<CommentDto> commentDtoList = sourcePostOptional.get()
                        .getComments()
                        .stream()
                        .map(commentMapper::mapCommentToDto)
                        .collect(Collectors.toList());
                return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
            }

        }

        // TODO add exception
        return null;
    }


}
