package com.feljtech.istudybucket.config.constant;

import com.feljtech.istudybucket.api.dto.BucketDto;

public class DefaultDto {
    public static final BucketDto DEFAULT_BUCKET_DTO = BucketDto.builder()
            .bucketId(10L)
            .bucketTitle("Default BucketDto")
            .description("MY default bucket dto")
            .creationDate("2020-01-01")
            .groupImage("/path/to/image")
            .memberCount(0)
            .build();
}
