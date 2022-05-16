package com.elroykanye.istudybucket.api.controller;

import com.elroykanye.istudybucket.api.dto.VoteDto;
import com.elroykanye.istudybucket.business.service.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/vote")
public class VoteController {
    private final VoteService voteService;

    @PostMapping(value = "")
    public ResponseEntity<String> addVote(@RequestBody VoteDto voteDto) {
        log.info("Adding vote for postId: {}", voteDto.getPostId());
        return ResponseEntity.ok(voteService.addVote(voteDto));
    }
}
