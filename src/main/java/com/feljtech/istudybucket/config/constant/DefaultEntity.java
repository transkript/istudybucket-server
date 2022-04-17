package com.feljtech.istudybucket.config.constant;

import com.feljtech.istudybucket.data.entity.*;
import com.feljtech.istudybucket.data.enums.Gender;
import com.feljtech.istudybucket.data.enums.PostType;
import com.feljtech.istudybucket.data.enums.UserRole;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class DefaultEntity {
    public final static Bucket DEFAULT_BUCKET = Bucket.builder()
            .bucketId(1L).bucketTitle("Default Bucket").description("This is the default bucket")
            .groupImage("/path/to/image").creationDate(Instant.now()).memberships(List.of())
            .build();

    public final static User DEFAULT_USER = User.builder()
            .userId(1L).username("user").password("password").email("user@mail.com")
            .firstName("First").lastName("Last").phoneNumber("1234567890")
            .dob(new Date()).gender(Gender.MALE).userRole(UserRole.USER).userVerified(true)
            .createdDate(Instant.now()).buckets(List.of()).posts(List.of()).votes(List.of()).messages(List.of())
            .trophies(List.of()).linkOfUsers(List.of()).linkToUsers(List.of()).buckets(List.of())
            .build();

    public final static Chat DEFAULT_CHAT = Chat.builder()
            .chatId(1L).chatTitle("Default Chat").chatType("GROUP")
            .chatDescription("This is the default chat").creationDate(Instant.now())
            .messages(List.of())
            .build();

    public final static Comment DEFAULT_COMMENT = Comment.builder()
            .commentId(1L).creationDate(Instant.now()).content("This is the default comment")
            .build();

    public final static Post DEFAULT_POST = Post.builder()
            .postId(1L).createdDate(Instant.now()).content("This is the default post")
            .postTitle("Default Post").postType(PostType.QUESTION)
            .votes(List.of())
            .comments(List.of())
            .build();
}
