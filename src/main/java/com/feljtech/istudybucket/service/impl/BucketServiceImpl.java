package com.feljtech.istudybucket.service.impl;

import com.feljtech.istudybucket.dto.BucketDto;
import com.feljtech.istudybucket.entity.Bucket;
import com.feljtech.istudybucket.repository.BucketRepository;
import com.feljtech.istudybucket.service.BucketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BucketServiceImpl implements BucketService {
    private final BucketRepository bucketRepository;

    @Override
    @Transactional
    public BucketDto createBucket(BucketDto bucketDto) {
        Bucket savedBucket = bucketRepository.save(dtoToBucket(bucketDto));
        bucketDto.setBucketId(savedBucket.getBucketId());
        return bucketDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BucketDto> getAllBuckets() {
        return bucketRepository.findAll()
                .stream().map(this::bucketToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BucketDto getBucketById(Long bucketId) {
        return bucketToDto(bucketRepository.findById(bucketId).orElseThrow());

    }

    private BucketDto bucketToDto(Bucket bucket) {
        return BucketDto.builder()
                .bucketId(bucket.getBucketId())
                .bucketTitle(bucket.getBucketTitle())
                .description(bucket.getDescription())
                .creatorName(bucket.getCreatorName())
                .groupImage(bucket.getGroupImage())
                .build();
    }

    private Bucket dtoToBucket(BucketDto bucketDto) {
        return Bucket.builder()
                .bucketTitle(bucketDto.getBucketTitle())
                .description(bucketDto.getDescription())
                .creatorName(bucketDto.getCreatorName())
                .creationDate(Instant.now())
                .groupImage(bucketDto.getGroupImage())
                .build();
    }


}
