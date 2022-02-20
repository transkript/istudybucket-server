package com.feljtech.istudybucket.data.repository;

import com.feljtech.istudybucket.data.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
