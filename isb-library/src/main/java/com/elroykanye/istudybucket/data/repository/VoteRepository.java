package com.elroykanye.istudybucket.data.repository;

import com.elroykanye.istudybucket.data.entity.User;
import com.elroykanye.istudybucket.data.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByUser(User user);
}
