package com.elroykanye.istudybucket.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author Elroy Kanye
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    @JsonProperty(value = "comment_id")
    private Long commentId;

    @JsonProperty(value = "creation_date")
    private String creationDate;

    @JsonProperty(value = "content")
    private String content;

    // from relationships
    @JsonProperty(value = "post_id")
    private Long postId;

    @JsonProperty(value = "author_id")
    private Long authorId;
}
