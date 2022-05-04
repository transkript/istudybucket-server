package com.elroykanye.istudybucket.business.mapper;

import com.elroykanye.istudybucket.api.dto.UserDto;
import com.elroykanye.istudybucket.data.entity.User;
import com.elroykanye.istudybucket.data.enums.Gender;
import com.elroykanye.istudybucket.data.enums.UserRole;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface UserMapper {
    @Mappings({
            @Mapping(target = "userRole", expression = "java(mapUserRole(user))"),
            @Mapping(target = "password", ignore = true),
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
            @Mapping(target = "userRole", expression = "java(inverseMapUserRole(userDto.getUserRole()))"),
            @Mapping(target = "gender", expression = "java(inverseMapUserGender(userDto.getGender()))")
    })
    User mapDtoToUser(UserDto userDto);

    default String mapUserRole(User user) {
        return user.getUserRole().name();
    }

    default UserRole inverseMapUserRole(String userRole) {
        for (UserRole role: UserRole.values()) {
            if(role.name().equalsIgnoreCase(userRole)) {
                return role;
            }
        }
        return null;
    }

    default Gender inverseMapUserGender(String userGender) {
        for(Gender gender: Gender.values()) {
            if(gender.name().equalsIgnoreCase(userGender)) {
                return gender;
            }
        }
        return null;
    }
}
