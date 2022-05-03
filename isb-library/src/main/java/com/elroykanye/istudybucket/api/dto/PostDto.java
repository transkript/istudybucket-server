package com.elroykanye.istudybucket.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
public class PostDto {
    @JsonProperty(value = "post_id")
    private Long postId;

    @JsonProperty(value = "post_title")
    private String postTitle;

    @JsonProperty(value = "content")
    private String content;

    @JsonProperty(value = "up_votes")
    private Integer upVotes;

    @JsonProperty(value = "down_votes")
    private Integer downVotes;

    @JsonProperty(value = "vote_count")
    private Integer voteCount;

    @JsonProperty(value = "comment_count")
    private Integer commentCount;

    @JsonProperty(value = "post_type")
    private String postType;

    @JsonProperty(value = "created_date")
    private String createdDate;

    // from relationship
    @JsonProperty(value = "author_id")
    private Long authorId;
}