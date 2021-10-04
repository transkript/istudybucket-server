package com.feljtech.istudybucket.mapper;

import com.feljtech.istudybucket.api.dto.BucketDto;
import com.feljtech.istudybucket.business.mapper.BucketMapper;
import com.feljtech.istudybucket.data.entity.Bucket;
import com.feljtech.istudybucket.data.entity.relation.UserInBucket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BucketMapperTest {

    @Autowired
    private BucketMapper bucketMapper;

    @Test
    void mapBucketToDto() {
        List<UserInBucket> bucketMemberships = List.of();

        Bucket bucket = Bucket.builder()
                .bucketId(1L)
                .bucketTitle("Test title")
                .description("Test description")
                .creatorName("Elroy Kanye")
                .groupImage("Group image")
                .creationDate(Instant.now())
                .memberships(bucketMemberships)
                .build();

        BucketDto bucketDto = bucketMapper.mapBucketToDto(bucket);

        assertNotNull(bucketDto);
        assertEquals(bucket.getBucketId(), bucketDto.getBucketId());
        assertEquals(bucket.getDescription(), bucketDto.getDescription());
        assertEquals(
                bucketMapper.mapCreationDate(bucket.getCreationDate()),
                bucketDto.getCreationDate()
        );
    }

    @Test
    void mDtoToBucket() {
        BucketDto bucketDto = BucketDto.builder()
                .bucketId(1L)
                .bucketTitle("Test title")
                .description("Test description")
                .creatorName("Elroy Kanye")
                .groupImage("group image")
                .build();

        Bucket bucket = bucketMapper.mapDtoToBucket(bucketDto);

        assertNotNull(bucket);
        assertEquals(bucketDto.getBucketTitle(), bucket.getBucketTitle());
        assertEquals(bucketDto.getGroupImage(), bucket.getGroupImage());
    }

}