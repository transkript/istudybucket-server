package com.feljtech.istudybucket.api.controller;

import com.feljtech.istudybucket.api.dto.VoteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/comment")
public class VoteController {

    @PostMapping(value = "")
    public ResponseEntity<String> addVote(@RequestBody VoteDto voteDto) {
        return null;
    }
}
