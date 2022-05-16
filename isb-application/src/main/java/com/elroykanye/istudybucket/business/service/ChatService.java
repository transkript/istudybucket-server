package com.elroykanye.istudybucket.business.service;

import com.elroykanye.istudybucket.api.dto.ChatDto;
import com.elroykanye.istudybucket.api.dto.response.EntityResponse;
import com.elroykanye.istudybucket.data.entity.Chat;

import java.util.List;

public interface ChatService {
    EntityResponse addChat(ChatDto chatDto);

    List<ChatDto> getChats();

    ChatDto getChat(Long id);

    EntityResponse deleteChat(Long id);

    EntityResponse updateChat(Long id, ChatDto chatDto);

    // entity methods
    Chat getChatById(Long id);
}
