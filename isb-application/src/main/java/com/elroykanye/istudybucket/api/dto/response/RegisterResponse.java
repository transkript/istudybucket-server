package com.elroykanye.istudybucket.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("message")
    private String message;
}
