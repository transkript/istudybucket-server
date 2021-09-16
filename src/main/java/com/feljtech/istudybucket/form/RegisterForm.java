package com.feljtech.istudybucket.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterForm {
    private String username;
    private String firstName;
    private String lastName;

}
