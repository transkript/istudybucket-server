package com.feljtech.istudybucket.config.constant;

import com.feljtech.istudybucket.api.dto.BucketDto;
import com.feljtech.istudybucket.api.dto.CommentDto;
import com.feljtech.istudybucket.api.dto.PostDto;

public class DefaultDto {
    public static final BucketDto DEFAULT_BUCKET_DTO = BucketDto.builder()
            .bucketId(10L).bucketTitle("Default BucketDto").description("MY default bucket dto")
            .creationDate("2020-01-01").groupImage("/path/to/image").memberCount(0)
            .build();

    public static final CommentDto DEFAULT_COMMENT_DTO = CommentDto.builder()
            .commentId(10L).content("Default CommentDto").creationDate("2020-01-01")
            .postId(10L).authorId(10L)
            .build();

    public static final PostDto DEFAULT_POST_DTO = PostDto.builder()
            .postId(10L).postTitle("Default PostDto").content("This is a default post").createdDate("2020-01-01")
            .postType("RESOURCE").upVotes(0).downVotes(0).commentCount(0)

            .authorId(10L)
            .build();
}
