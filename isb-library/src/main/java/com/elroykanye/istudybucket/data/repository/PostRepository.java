package com.elroykanye.istudybucket.data.repository;

import com.elroykanye.istudybucket.data.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
