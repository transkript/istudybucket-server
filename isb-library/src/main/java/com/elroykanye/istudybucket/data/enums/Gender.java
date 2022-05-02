package com.elroykanye.istudybucket.data.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum Gender {
    MALE("male"),
    FEMALE("female"),
    OTHER("other");

    private String gender;
}
