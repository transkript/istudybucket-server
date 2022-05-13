package com.elroykanye.istudybucket.business.mapper;

import com.elroykanye.istudybucket.config.constant.DefaultDto;
import com.elroykanye.istudybucket.config.constant.DefaultEntity;
import com.elroykanye.istudybucket.api.dto.BucketDto;
import com.elroykanye.istudybucket.data.entity.Bucket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BucketMapperTest {
    private final BucketMapper bucketMapper;

    private Bucket bucket;
    private BucketDto bucketDto;

    @Autowired
    public BucketMapperTest(BucketMapper bucketMapper) {
        this.bucketMapper = bucketMapper;
    }

    @BeforeEach
    void setUp() {
        bucket = DefaultEntity.DEFAULT_BUCKET;
        bucketDto = DefaultDto.DEFAULT_BUCKET_DTO;

        bucket.setCreator(DefaultEntity.DEFAULT_USER);
        bucket.setChatRoom(DefaultEntity.DEFAULT_CHAT);
    }

    @Test
    void mapBucketToDto() {
        BucketDto bucketDto1 = bucketMapper.mapBucketToDto(bucket);
        assertEquals(bucket.getBucketId(), bucketDto1.getBucketId());
        assertEquals(bucket.getBucketTitle(), bucketDto1.getBucketTitle());
        assertEquals(bucket.getDescription(), bucketDto1.getDescription());
        assertEquals(bucket.getCreationDate(), bucketDto1.getCreationDate());
        assertEquals(bucket.getGroupImage(), bucketDto1.getGroupImage());

        assertEquals(bucket.getMemberships().size(), bucketDto1.getMemberCount());
        assertEquals(bucket.getChatRoom().getChatId(), bucketDto1.getChatId());
        assertEquals(bucket.getCreator().getUserId(), bucketDto1.getCreatorId());
    }

    @Test
    void mapDtoToBucket() {
        Bucket bucket1 = bucketMapper.mapDtoToBucket(bucketDto);
        assertEquals(bucketDto.getBucketId(), bucket1.getBucketId());
        assertEquals(bucketDto.getBucketTitle(), bucket1.getBucketTitle());
        assertEquals(bucketDto.getDescription(), bucket1.getDescription());
        assertEquals(bucketDto.getGroupImage(), bucket1.getGroupImage());
    }
}
