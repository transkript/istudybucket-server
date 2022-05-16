package com.elroykanye.istudybucket.business.service.impl;

import com.elroykanye.istudybucket.api.dto.BucketDto;
import com.elroykanye.istudybucket.business.mapper.BucketMapper;
import com.elroykanye.istudybucket.business.service.BucketService;
import com.elroykanye.istudybucket.data.entity.Bucket;
import com.elroykanye.istudybucket.data.repository.BucketRepository;
import com.elroykanye.istudybucket.excetion.EntityException;
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
        bucketDto.setId(savedBucket.getId());
        return bucketDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BucketDto> getAllBuckets() {
        return getBuckets().stream().map(bucketMapper::mapBucketToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BucketDto getBucketById(Long bucketId) {
        return bucketMapper.mapBucketToDto(getBucket(bucketId));
    }

    @Override
    public Bucket getBucket(Long id) {
        return bucketRepository.findById(id).orElseThrow(() -> {
            throw new EntityException.EntityNotFoundException("bucket", id);
        });
    }

    @Override
    public List<Bucket> getBuckets() {
        return bucketRepository.findAll();
    }

}
