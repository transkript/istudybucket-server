package com.elroykanye.istudybucket.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntityResponse {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "message")
    private String message;

    @JsonProperty(value = "entity")
    private String entity;
}
