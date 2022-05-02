package com.elroykanye.istudybucket.business.service;

import com.elroykanye.istudybucket.api.dto.BucketDto;

import java.util.List;

public interface BucketService {
    BucketDto createBucket(BucketDto newBucket);

    List<BucketDto> getAllBuckets();

    BucketDto getBucketById(Long bucketId);
}
