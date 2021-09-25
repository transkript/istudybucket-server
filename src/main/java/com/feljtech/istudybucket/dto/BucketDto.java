package com.feljtech.istudybucket.dto;

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

    @JsonProperty(value = "group_image")
    private String groupImage;

    @JsonProperty(value = "number_of_members")
    private Long numberOfMembers;
}
