package com.feljtech.istudybucket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VoteDto {
    @JsonProperty(value = "user_id")
    private Long userId;

    @JsonProperty(value = "post_id")
    private Long postId;

    @JsonProperty(value = "vote")
    private Integer vote;
}
