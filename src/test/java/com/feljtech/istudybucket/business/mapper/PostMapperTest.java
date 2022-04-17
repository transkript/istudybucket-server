package com.feljtech.istudybucket.business.mapper;

import com.feljtech.istudybucket.api.dto.PostDto;
import com.feljtech.istudybucket.config.constant.DefaultDto;
import com.feljtech.istudybucket.config.constant.DefaultEntity;
import com.feljtech.istudybucket.data.entity.Post;
import com.feljtech.istudybucket.data.enums.PostType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostMapperTest {
    private final PostMapper postMapper;

    private Post post;
    private PostDto postDto;

    @Autowired
    public PostMapperTest(PostMapper postMapper) {
        this.postMapper = postMapper;
    }
    @BeforeEach
    void setUp() {
        post = DefaultEntity.DEFAULT_POST;
        postDto = DefaultDto.DEFAULT_POST_DTO;

        post.setAuthor(DefaultEntity.DEFAULT_USER);
    }

    @Test
    void mapPostToDto() {
        PostDto postDto1 = postMapper.mapPostToDto(post);

        assertEquals(post.getPostId(), postDto1.getPostId());
        assertEquals(post.getPostTitle(), postDto1.getPostTitle());
        assertEquals(post.getContent(), postDto1.getContent());
        assertEquals(post.getPostType(), PostType.valueOf(postDto1.getPostType()));
        assertEquals(post.getCreatedDate().toString(), postDto1.getCreatedDate());


        assertEquals(post.getAuthor().getUserId(), postDto1.getAuthorId());
        assertEquals(post.getComments().size(), postDto1.getCommentCount());
        assertEquals(post.getVotes().size(), postDto1.getVoteCount());
    }

    @Test
    void mapDtoToPost() {
        Post post1 = postMapper.mapDtoToPost(postDto);

        assertEquals(postDto.getPostId(), post1.getPostId());
        assertEquals(postDto.getPostTitle(), post1.getPostTitle());
        assertEquals(postDto.getContent(), post1.getContent());
        assertEquals(PostType.valueOf(postDto.getPostType()), post1.getPostType());

    }
}
