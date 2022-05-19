package com.elroykanye.istudybucket.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BucketDto {
    @JsonProperty(value = "bucket_id")
    private Long id;

    @JsonProperty(value = "bucket_title")
    @NotBlank
    private String title;

    @JsonProperty(value = "bucket_desc")
    @NotNull
    private String description;

    @JsonProperty(value = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonProperty(value = "updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

    @JsonProperty(value = "group_image")
    private String groupImage;

    @JsonProperty(value = "member_count")
    private Integer memberCount;

    // from relationships
    @JsonProperty("creator_id")
    @NotNull
    private Long creatorId;

    @JsonProperty("default_chat_id")
    private Long defaultChatId;
}
