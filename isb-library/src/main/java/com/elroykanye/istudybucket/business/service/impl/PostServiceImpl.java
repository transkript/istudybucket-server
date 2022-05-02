package com.elroykanye.istudybucket.business.service.impl;

import com.elroykanye.istudybucket.business.mapper.PostMapper;
import com.elroykanye.istudybucket.business.service.PostService;
import com.elroykanye.istudybucket.api.dto.PostDto;
import com.elroykanye.istudybucket.data.entity.Post;
import com.elroykanye.istudybucket.data.entity.User;
import com.elroykanye.istudybucket.data.repository.PostRepository;
import com.elroykanye.istudybucket.data.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseEntity<String> addPost(PostDto postDto) {
        AtomicBoolean postAdded = new AtomicBoolean(false);
        Optional<User> userOptional = userRepository.findById(postDto.getAuthorId());
        userOptional.ifPresentOrElse(
                user -> {
                    Post post = postMapper.mapDtoToPost(postDto);
                    post.setAuthor(user);
                    postRepository.save(post);
                    postAdded.set(true);
                },
                () -> postAdded.set(false)
        );
        return postAdded.get() ?
                new ResponseEntity<>("Post added", HttpStatus.CREATED):
                new ResponseEntity<>("Post not added", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapPostToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostsByAuthorId(Long authorId) {
        return getAllPosts()
                .stream()
                .filter(postDto -> postDto.getAuthorId().equals(authorId))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> getPostById(Long postId) {
        AtomicBoolean postFound = new AtomicBoolean(false);
        Optional<Post> postOptional = postRepository.findById(postId);
        postOptional.ifPresentOrElse(
                post -> postFound.set(true),
                () -> postFound.set(false)
        );
        return postFound.get() ?
                new ResponseEntity<>(postMapper.mapPostToDto(postOptional.orElseThrow()), HttpStatus.FOUND) :
                new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
    }
}
