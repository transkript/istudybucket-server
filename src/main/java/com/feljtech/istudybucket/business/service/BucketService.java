package com.feljtech.istudybucket.business.service;

import com.feljtech.istudybucket.api.dto.BucketDto;

import java.util.List;

public interface BucketService {
    BucketDto createBucket(BucketDto newBucket);

    List<BucketDto> getAllBuckets();

    BucketDto getBucketById(Long bucketId);
}
