package com.feljtech.istudybucket.mapper;

import com.feljtech.istudybucket.dto.BucketDto;
import com.feljtech.istudybucket.entity.Bucket;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

@SpringBootTest
class BucketMapperTest {
    private final BucketMapper bucketMapper;// = Mappers.getMapper(BucketMapper.class);

    BucketMapperTest(BucketMapper bucketMapper) {
        this.bucketMapper = bucketMapper;
    }


    @Test
    void mapBucketToDto() {
        Bucket bucket = Bucket.builder()
                .bucketTitle("Test Bucket")
                .description("My test bucket")
                .creatorName("Elroy Kanye")
                .creationDate(Instant.now())
                .groupImage("groupImage")
                .participants(3L).build();
        BucketDto bucketDto = bucketMapper.mapBucketToDto(bucket);

        //assertSame(bucketDto, bucket);
    }


}