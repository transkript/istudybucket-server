package com.feljtech.istudybucket.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum PostType {
    QUESTION("QUESTION"),
    RESOURCE("RESOURCE");

    private String postType;
}
