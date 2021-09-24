package com.feljtech.istudybucket.service;

import com.feljtech.istudybucket.dto.BucketDto;

import java.util.List;

public interface BucketService {
    BucketDto createBucket(BucketDto newBucket);

    List<BucketDto> getAllBuckets();

    BucketDto getAllBucketsById(Long bucketId);
}
