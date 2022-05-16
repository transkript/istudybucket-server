package com.elroykanye.istudybucket.business.mapper;

import com.elroykanye.istudybucket.api.dto.ChatDto;
import com.elroykanye.istudybucket.data.entity.Bucket;
import com.elroykanye.istudybucket.data.entity.Chat;
import com.elroykanye.istudybucket.data.entity.relation.UserInChat;
import com.elroykanye.istudybucket.data.enums.ChatType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface ChatMapper {
    @Mappings({
            @Mapping(target = "bucketId", expression = "java(mapBucket(chat.getBucket()))"),
            @Mapping(target = "creatorId", expression = "java(chat.getCreator().getId())"),
            @Mapping(target = "participants", expression = "java(mapParticipants(chat.getUsersInChat()))"),
            @Mapping(target = "type", expression = "java(chat.getType().ordinal())"),
    })
    ChatDto mapChatToDto(Chat chat);

    default Long mapBucket(Bucket bucket) {
        if (bucket == null) return null;
        return bucket.getId();
    }

    default List<Long> mapParticipants(Set<UserInChat> usersInChat) {
        return usersInChat.stream()
                .map(userInChat -> userInChat.getParticipant().getId()).collect(Collectors.toList());
    }

    default Integer mapType(ChatType chatType) {
        return chatType.ordinal();
    }

    @Mappings({
            @Mapping(target = "bucket", ignore = true),
            @Mapping(target = "chatMessages", ignore = true),
            @Mapping(target = "creator", ignore = true),
            @Mapping(target = "usersInChat", ignore = true),
            @Mapping(target = "type", expression = "java(inverseMapType(chatDto.getType()))")
    })
    @InheritInverseConfiguration
    Chat mapDtoToChat(ChatDto chatDto);

    default ChatType inverseMapType(Integer type) {
        if(type == null) return ChatType.GROUP_PUBLIC;
        return switch (type) {
            case 0 -> ChatType.ONE_ON_ONE;
            case 1 -> ChatType.GROUP_PRIVATE;
            default -> ChatType.GROUP_PUBLIC;
        };
    }
}
