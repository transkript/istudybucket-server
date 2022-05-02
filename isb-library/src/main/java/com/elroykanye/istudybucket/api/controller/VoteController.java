package com.elroykanye.istudybucket.api.controller;

import com.elroykanye.istudybucket.api.dto.VoteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/comment")
public class VoteController {

    @SuppressWarnings("SameReturnValue")
    @PostMapping(value = "")
    public ResponseEntity<String> addVote(@RequestBody VoteDto voteDto) {
        return null;
    }
}
