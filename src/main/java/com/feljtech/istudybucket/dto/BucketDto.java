package com.feljtech.istudybucket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

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
    private String bucketDescription;

    @JsonProperty(value = "creator_name")
    private String creatorName;

    @JsonProperty(value = "group_image")
    private String groupImage;
}
