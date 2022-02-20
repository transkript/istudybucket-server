package com.feljtech.istudybucket.business.service.impl;

import com.feljtech.istudybucket.api.dto.BucketDto;
import com.feljtech.istudybucket.data.entity.Bucket;
import com.feljtech.istudybucket.business.mapper.BucketMapper;
import com.feljtech.istudybucket.data.repository.BucketRepository;
import com.feljtech.istudybucket.business.service.BucketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BucketServiceImpl implements BucketService {
    private final BucketRepository bucketRepository;
    private final BucketMapper bucketMapper;

    @Override
    @Transactional
    public BucketDto createBucket(BucketDto bucketDto) {
        Bucket savedBucket = bucketRepository.save(bucketMapper.mapDtoToBucket(bucketDto));
        bucketDto.setBucketId(savedBucket.getBucketId());
        return bucketDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BucketDto> getAllBuckets() {
        return bucketRepository.findAll()
                .stream().map(bucketMapper::mapBucketToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BucketDto getBucketById(Long bucketId) {
        return bucketMapper.mapBucketToDto(bucketRepository.findById(bucketId).orElseThrow());
    }

}
