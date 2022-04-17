package com.feljtech.istudybucket.business.mapper;

import com.feljtech.istudybucket.api.dto.CommentDto;
import com.feljtech.istudybucket.config.constant.DefaultDto;
import com.feljtech.istudybucket.config.constant.DefaultEntity;
import com.feljtech.istudybucket.data.entity.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CommentMapperTest {
    private final CommentMapper commentMapper;

    private Comment comment;
    private CommentDto commentDto;

    @Autowired
    public CommentMapperTest(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @BeforeEach
    void setUp() {
        comment = DefaultEntity.DEFAULT_COMMENT;
        commentDto = DefaultDto.DEFAULT_COMMENT_DTO;

        comment.setSourcePost(DefaultEntity.DEFAULT_POST);
        comment.setAuthor(DefaultEntity.DEFAULT_USER);
    }

    @Test
    void mapCommentToDto() {
        CommentDto commentDto1 = commentMapper.mapCommentToDto(comment);
        assertEquals(comment.getCommentId(), commentDto1.getCommentId());
        assertEquals(comment.getContent(), commentDto1.getContent());
        assertEquals(comment.getCreationDate().toString().split("T")[0], commentDto1.getCreationDate());

        assertEquals(comment.getSourcePost().getPostId(), commentDto1.getPostId());
        assertEquals(comment.getAuthor().getUserId(), commentDto1.getAuthorId());
    }

    @Test
    void mapDtoToComment() {
        Comment comment1 = commentMapper.mapDtoToComment(commentDto);
        assertEquals(commentDto.getCommentId(), comment1.getCommentId());
        assertEquals(commentDto.getContent(), comment1.getContent());

    }
}
