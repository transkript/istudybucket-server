package com.elroykanye.istudybucket.business.service;

import com.elroykanye.istudybucket.api.dto.ChatMessageDto;
import com.elroykanye.istudybucket.api.dto.response.EntityResponse;

import java.util.List;

public interface ChatMessageService {
    EntityResponse addChatMessage(Long chatId, ChatMessageDto chatMessageDto);

    List<ChatMessageDto> getChatMessages(Long chatId);
}
