package com.feljtech.istudybucket.data.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER"),
    TEACHER("TEACHER"),
    STUDENT("STUDENT");

    private String userRole;
}
