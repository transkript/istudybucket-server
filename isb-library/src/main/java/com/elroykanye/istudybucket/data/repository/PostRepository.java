package com.elroykanye.istudybucket.data.repository;

import com.elroykanye.istudybucket.data.entity.Post;
import com.elroykanye.istudybucket.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySourcePost(Post sourcePost);
    List<Post> findAllBySourcePostAndAuthor(Post sourcePost, User author);
}
