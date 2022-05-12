package com.elroykanye.istudybucket.business.service.impl;

import com.elroykanye.istudybucket.api.dto.VoteDto;
import com.elroykanye.istudybucket.business.mapper.VoteMapper;
import com.elroykanye.istudybucket.business.service.PostService;
import com.elroykanye.istudybucket.business.service.UserService;
import com.elroykanye.istudybucket.business.service.VoteService;
import com.elroykanye.istudybucket.data.entity.Post;
import com.elroykanye.istudybucket.data.entity.User;
import com.elroykanye.istudybucket.data.entity.Vote;
import com.elroykanye.istudybucket.data.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {
    private final VoteMapper voteMapper;
    private final UserService userService;
    private final PostService postService;
    private final VoteRepository voteRepository;

    @Override
    @Transactional
    public String addVote(VoteDto voteDto) {
        Post post = postService.getPost(voteDto.getPostId());
        User user = userService.getUser(voteDto.getUserId());


        Vote vote = voteMapper.mapDtoToVote(voteDto);
        Optional<Vote> voteOptional = voteRepository.findByUser(user);
        if(voteDto.getVoteId() == null && voteOptional.isPresent()) {
            vote.setVoteId(voteOptional.get().getVoteId());
        }

        vote.setUser(user);
        vote.setPost(post);
        voteRepository.save(vote);

        post.getVotes().add(vote);
        postService.updatePost(post);
        return "Added vote successfully";
    }
}
