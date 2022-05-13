package com.elroykanye.istudybucket.business.mapper;

import com.elroykanye.istudybucket.api.dto.BucketDto;
import com.elroykanye.istudybucket.data.entity.Bucket;
import com.elroykanye.istudybucket.data.entity.relation.UserInBucket;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface BucketMapper {
    @Mappings({
            @Mapping(target = "memberCount", expression = "java(mapMemberships(bucket.getMemberships()))"),
            @Mapping(target = "creatorId", expression = "java(bucket.getCreator().getUserId())"),
            @Mapping(target = "chatId", expression = "java(bucket.getChatRoom().getChatId())"),
    })
    BucketDto mapBucketToDto(Bucket bucket);

    default Integer mapMemberships(List<UserInBucket> memberships) {
        return memberships.size();
    }


    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "memberships", ignore = true),
            @Mapping(target = "creationDate", ignore = true),
            @Mapping(target = "chatRoom", ignore = true),
            @Mapping(target = "creator", ignore = true),
    })
    Bucket mapDtoToBucket(BucketDto bucketDto);
}
