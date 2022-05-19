package com.elroykanye.istudybucket.business.service;

import com.elroykanye.istudybucket.api.dto.BucketDto;
import com.elroykanye.istudybucket.api.dto.response.EntityResponse;
import com.elroykanye.istudybucket.data.entity.Bucket;

import java.util.List;

public interface BucketService {
    EntityResponse createBucket(BucketDto newBucket);

    List<BucketDto> getAllBuckets();

    BucketDto getBucketById(Long bucketId);

    EntityResponse updateBucket(Long bucketId, BucketDto bucketDto);
    Void deleteBucketById(Long bucketId);
    // entity
    Bucket getBucketEntity(Long id);
    List<Bucket> getBuckets();


}
