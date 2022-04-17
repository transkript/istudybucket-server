package com.feljtech.istudybucket.business.mapper;

import com.feljtech.istudybucket.api.dto.UserDto;
import com.feljtech.istudybucket.data.entity.User;
import com.feljtech.istudybucket.data.enums.UserRole;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface UserMapper {
    @Mappings({
        @Mapping(target = "userRole", expression = "java(mapUserRole(user))")
    })
    UserDto mapUserToDto(User user);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "posts", ignore = true),
            @Mapping(target = "comments", ignore = true),
            @Mapping(target = "memberships", ignore = true),
            @Mapping(target = "votes", ignore = true),
            @Mapping(target = "address", ignore = true),
            @Mapping(target = "messages", ignore = true),
            @Mapping(target = "trophies", ignore = true),
            @Mapping(target = "linkToUsers", ignore = true),
            @Mapping(target = "linkOfUsers", ignore = true),
            @Mapping(target = "buckets", ignore = true),
            @Mapping(target = "userRole", expression = "java(inverseMapUserRole(userDto))")
    })
    User mapDtoToUser(UserDto userDto);

    default String mapUserRole(User user) {
        return user.getUserRole().name();
    }

    default UserRole inverseMapUserRole(UserDto userDto) {
        for (UserRole role: UserRole.values()) {
            if(role.name().equals(userDto.getUserRole())) {
                return UserRole.valueOf(userDto.getUserRole());
            }
        }
        return null;
    }
}
