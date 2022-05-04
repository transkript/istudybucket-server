package com.elroykanye.istudybucket.business.service.impl;

import com.elroykanye.istudybucket.api.dto.PostDto;
import com.elroykanye.istudybucket.business.mapper.PostMapper;
import com.elroykanye.istudybucket.business.service.PostService;
import com.elroykanye.istudybucket.data.entity.Post;
import com.elroykanye.istudybucket.data.repository.PostRepository;
import com.elroykanye.istudybucket.data.repository.UserRepository;
import com.elroykanye.istudybucket.excetion.EntityException;
import com.elroykanye.istudybucket.excetion.IstudybucketException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public String addPost(PostDto postDto) {
        userRepository.findById(postDto.getAuthorId()).ifPresentOrElse(
                user -> {
                    log.info("Post author is valid");
                    if(postDto.getPostId() != null) {
                        postRepository.findById(postDto.getPostId()).ifPresentOrElse(
                                post -> {
                                    log.warn("Post with id {} already exists", postDto.getPostId());
                                    throw new EntityException.EntityAlreadyExists("post", postDto.getPostId());
                                },
                                ()-> {
                                    log.info("Post with id {} not found, creating new post", postDto.getPostId());
                                    Post post = postMapper.mapDtoToPost(postDto);
                                    post.setAuthor(user);
                                    postRepository.save(post);
                                });
                    }},
                () -> {
                    log.error("Post author is invalid");
                    throw new IstudybucketException.NotAuthorisedException("Invalid user id " + postDto.getAuthorId() + " for post");
                }
        );
        return "Post created successfully";
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::mapPostToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostsByAuthorId(Long authorId) {
        return getAllPosts().stream()
                .filter(postDto -> postDto.getAuthorId().equals(authorId))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if(postOptional.isPresent()) {
            log.info("Post with id {} found", postId);
            return postMapper.mapPostToDto(postOptional.get());
        }
        log.warn("Post with id {} not found", postId);
        throw new EntityException.EntityNotFoundException("post", postId);
    }
}
