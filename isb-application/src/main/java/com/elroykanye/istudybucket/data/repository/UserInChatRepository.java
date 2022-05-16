package com.elroykanye.istudybucket.data.repository;

import com.elroykanye.istudybucket.data.entity.composite.UserInChatKey;
import com.elroykanye.istudybucket.data.entity.relation.UserInChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInChatRepository extends JpaRepository<UserInChat, UserInChatKey> {
}
