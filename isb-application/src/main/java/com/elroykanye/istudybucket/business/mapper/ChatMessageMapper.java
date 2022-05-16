package com.elroykanye.istudybucket.business.mapper;


import com.elroykanye.istudybucket.api.dto.ChatMessageDto;
import com.elroykanye.istudybucket.data.entity.ChatMessage;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface ChatMessageMapper {
    @Mappings({
            @Mapping(target = "chatId", expression = "java(chatMessage.getChat().getId())"),
            @Mapping(target = "senderId", expression = "java(chatMessage.getSender().getId())"),
    })
    ChatMessageDto mapChatMessageToDto(ChatMessage chatMessage);

    @Mappings({
            @Mapping(target = "chat", ignore = true),
            @Mapping(target = "sender", ignore = true),
    })
    @InheritInverseConfiguration
    ChatMessage mapDtoToChatMessage(ChatMessageDto chatMessageDto);
}
