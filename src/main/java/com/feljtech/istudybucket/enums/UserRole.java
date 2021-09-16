package com.feljtech.istudybucket.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum UserRole {
    ADMIN(101),
    TEACHER(202),
    STUDENT(303);

    private int userRole;
}
