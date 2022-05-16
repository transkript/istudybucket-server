package com.elroykanye.istudybucket.api.controller;

import com.elroykanye.istudybucket.api.dto.ChatDto;
import com.elroykanye.istudybucket.api.dto.response.EntityResponse;
import com.elroykanye.istudybucket.business.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/chat")
public class ChatController {
    private final ChatService chatService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> addChat(@RequestBody ChatDto chatDto) {
        return new ResponseEntity<>(chatService.addChat(chatDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ChatDto>> getChats() {
        return ResponseEntity.ok(chatService.getChats());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChatDto> getChat(@PathVariable Long id) {
        return ResponseEntity.ok(chatService.getChat(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<EntityResponse> deleteChat(@PathVariable Long id) {
        return ResponseEntity.ok(chatService.deleteChat(id));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> updateChat(@PathVariable Long id, @RequestBody ChatDto chatDto) {
        return ResponseEntity.ok(chatService.updateChat(id, chatDto));
    }
}
