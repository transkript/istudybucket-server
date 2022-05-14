package com.elroykanye.istudybucket.config.constant;

import com.elroykanye.istudybucket.api.dto.BucketDto;
import com.elroykanye.istudybucket.api.dto.PostDto;
import com.elroykanye.istudybucket.api.dto.UserDto;
import com.elroykanye.istudybucket.data.enums.Gender;
import com.elroykanye.istudybucket.data.enums.UserRole;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class DefaultDto {
    public static final BucketDto DEFAULT_BUCKET_DTO = BucketDto.builder()
            .bucketId(10L).bucketTitle("Default BucketDto").description("MY default bucket dto")
            .creationDate(LocalDateTime.now()).groupImage("/path/to/image").memberCount(0)
            .build();

    public static final PostDto DEFAULT_POST_DTO = PostDto.builder()
            .postId(10L).postTitle("Default PostDto").content("This is a default post").createdDate(LocalDateTime.now())
            .postType("RESOURCE").upVotes(0).downVotes(0).commentCount(0)
            .authorId(10L)
            .build();

    public static final UserDto DEFAULT_USER_DTO = UserDto.builder()
            .userId(10L).username("userdto").email("userdto@email.com").password("password")
            .firstName("User").lastName("Dto").phoneNumber("1234567890").dob(LocalDateTime.now())
            .gender(Gender.MALE.name()).userRole(UserRole.USER.name()).createdDate(LocalDateTime.now())
            .userVerified(true)
            .build();
}
