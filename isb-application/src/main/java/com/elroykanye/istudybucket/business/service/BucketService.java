package com.elroykanye.istudybucket.business.service;

import com.elroykanye.istudybucket.api.dto.BucketDto;
import com.elroykanye.istudybucket.data.entity.Bucket;

import java.util.List;

public interface BucketService {
    BucketDto createBucket(BucketDto newBucket);

    List<BucketDto> getAllBuckets();

    BucketDto getBucketById(Long bucketId);

    // entity
    Bucket getBucket(Long id);
    List<Bucket> getBuckets();
}
