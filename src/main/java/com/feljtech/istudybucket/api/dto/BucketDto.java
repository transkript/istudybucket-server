package com.feljtech.istudybucket.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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

    @JsonProperty(value = "creator_name")
    private String creatorName;

    @JsonProperty(value = "creation_date")
    private String creationDate;

    @JsonProperty(value = "group_image")
    private String groupImage;

    @JsonProperty(value = "member_count")
    private Integer memberCount;
}
