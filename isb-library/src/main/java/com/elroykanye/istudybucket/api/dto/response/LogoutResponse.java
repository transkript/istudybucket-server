package com.elroykanye.istudybucket.api.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LogoutResponse {
    private String username;
    private String message;
}
