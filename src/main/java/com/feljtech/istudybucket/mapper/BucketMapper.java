package com.feljtech.istudybucket.mapper;

import com.feljtech.istudybucket.dto.BucketDto;
import com.feljtech.istudybucket.entity.Bucket;
import com.feljtech.istudybucket.entity.relation.UserInBucket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BucketMapper {

    @Mapping(
            target = "numberOfMembers",
            expression = "java(mapMemberships(bucket.getMemberships()))"
    )
    BucketDto mapBucketToDto(Bucket bucket);

    default Long mapMemberships(List<UserInBucket> memberships) {
        return (long) memberships.size();
    }
}
