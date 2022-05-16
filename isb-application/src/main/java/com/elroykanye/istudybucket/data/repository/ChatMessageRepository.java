package com.elroykanye.istudybucket.data.repository;

import com.elroykanye.istudybucket.data.entity.Chat;
import com.elroykanye.istudybucket.data.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findAllByChat(Chat chat);
}
