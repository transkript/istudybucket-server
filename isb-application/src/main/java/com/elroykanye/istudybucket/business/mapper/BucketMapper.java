package com.elroykanye.istudybucket.business.mapper;

import com.elroykanye.istudybucket.api.dto.BucketDto;
import com.elroykanye.istudybucket.data.entity.Bucket;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface BucketMapper {
    @Mappings({
            @Mapping(target = "memberCount", expression = "java(bucket.getMemberships().size())"),
            @Mapping(target = "creatorId", expression = "java(bucket.getCreator().getId())"),
    })
    BucketDto mapBucketToDto(Bucket bucket);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "memberships", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "chatRooms", ignore = true),
            @Mapping(target = "creator", ignore = true),
    })
    Bucket mapDtoToBucket(BucketDto bucketDto);
}
