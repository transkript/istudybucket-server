package com.elroykanye.istudybucket.business.service.impl;

import com.elroykanye.istudybucket.api.dto.ChatMessageDto;
import com.elroykanye.istudybucket.api.dto.response.EntityResponse;
import com.elroykanye.istudybucket.business.mapper.ChatMessageMapper;
import com.elroykanye.istudybucket.business.service.ChatMessageService;
import com.elroykanye.istudybucket.business.service.ChatService;
import com.elroykanye.istudybucket.business.service.UserService;
import com.elroykanye.istudybucket.data.entity.Chat;
import com.elroykanye.istudybucket.data.entity.ChatMessage;
import com.elroykanye.istudybucket.data.entity.User;
import com.elroykanye.istudybucket.data.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;
    private final ChatService chatService;
    private final UserService userService;

    @Override
    public EntityResponse addChatMessage(Long chatId, ChatMessageDto chatMessageDto) {
        if (chatId == null || chatMessageDto.getChatId() == null || !chatId.equals(chatMessageDto.getChatId())) {
            // TODO add appropriate exception handling
            throw new IllegalArgumentException("Chat ids must be equal in path and body");
        }
        if(chatMessageDto.getSenderId() == null) {
            throw new IllegalArgumentException("Sender id must not be null");
        }

        Chat chat = chatService.getChatEntity(chatId);
        User sender = userService.getUserEntity(chatMessageDto.getSenderId());
        ChatMessage chatMessage = chatMessageMapper.mapDtoToChatMessage(chatMessageDto);
        chatMessage.setId(null);
        chatMessage.setChat(chat);
        chatMessage.setCreatedAt(LocalDateTime.now());
        chatMessage.setSender(sender);
        return EntityResponse.builder().id(chatMessageRepository.save(chatMessage).getId())
                .message("Chat message added successfully").build();
    }

    @Override
    public List<ChatMessageDto> getChatMessages(Long chatId) {
        Chat chat = chatService.getChatEntity(chatId);
        return chatMessageRepository.findAllByChat(chat).stream().map(chatMessageMapper::mapChatMessageToDto)
                .collect(java.util.stream.Collectors.toList());
    }
}
