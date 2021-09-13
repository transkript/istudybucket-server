package com.feljtech.istudybucket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterForm {
   @JsonProperty("username")
   private String username;
   @JsonProperty("email")
   private String email;
   @JsonProperty("password")
   private String password;
}
