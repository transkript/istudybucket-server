package com.elroykanye.istudybucket.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BucketDto {
    @JsonProperty(value = "bucket_id")
    private Long bucketId;

    @JsonProperty(value = "bucket_title")
    private String bucketTitle;

    @JsonProperty(value = "bucket_desc")
    private String description;

    @JsonProperty(value = "creation_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

    @JsonProperty(value = "group_image")
    private String groupImage;

    @JsonProperty(value = "member_count")
    private Integer memberCount;

    // from relationships
    @JsonProperty("creator_id")
    private Long creatorId;

    @JsonProperty("chat_id")
    private Long chatId;
}
