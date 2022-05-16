package com.elroykanye.istudybucket.api.controller;

import com.elroykanye.istudybucket.api.dto.ChatMessageDto;
import com.elroykanye.istudybucket.api.dto.response.EntityResponse;
import com.elroykanye.istudybucket.business.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/chat/message")
public class ChatMessageController {
    private final ChatMessageService chatMessageService;

    @PostMapping(value = "/{chatId}")
    public ResponseEntity<EntityResponse> addChatMessage(@PathVariable Long chatId, @RequestBody ChatMessageDto chatMessageDto) {
        return new ResponseEntity<>(chatMessageService.addChatMessage(chatId, chatMessageDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{chatId}")
    public ResponseEntity<List<ChatMessageDto>> getChatMessages(@PathVariable Long chatId) {
        return ResponseEntity.ok(chatMessageService.getChatMessages(chatId));
    }
}
