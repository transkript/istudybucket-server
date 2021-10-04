package com.feljtech.istudybucket.business.mapper;

import com.feljtech.istudybucket.api.dto.BucketDto;
import com.feljtech.istudybucket.data.entity.Bucket;
import com.feljtech.istudybucket.data.entity.relation.UserInBucket;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.util.List;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
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
