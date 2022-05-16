package com.elroykanye.istudybucket.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteDto {
    @JsonProperty(value = "vote_id")
    private Long voteId;

    @JsonProperty(value = "user_id")
    private Long userId;

    @JsonProperty(value = "post_id")
    private Long postId;

    @JsonProperty(value = "vote")
    private Integer vote;
}
