package com.feljtech.istudybucket.mapper;

import com.feljtech.istudybucket.dto.BucketDto;
import com.feljtech.istudybucket.entity.Bucket;
import com.feljtech.istudybucket.entity.relation.UserInBucket;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BucketMapper {
    @Mapping(target = "memberCount", expression = "java(mapMemberships(bucket.getMemberships()))")
    @Mapping(target = "creationDate", expression = "java(mapCreationDate(bucket.getCreationDate()))")
    BucketDto mapBucketToDto(Bucket bucket);

    default Integer mapMemberships(List<UserInBucket> memberships) {
        return memberships.size();
    }

    default String mapCreationDate(Instant creationDate) {
        return creationDate.toString().split("T")[0];

    }

    @InheritInverseConfiguration
    @Mapping(target = "memberships", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    Bucket mapDtoToBucket(BucketDto bucketDto);
}
