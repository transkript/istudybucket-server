package com.elroykanye.istudybucket.data.repository;

import com.elroykanye.istudybucket.data.entity.Chat;
import com.elroykanye.istudybucket.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findAllByCreator(User creator);
}
