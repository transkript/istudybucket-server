package com.feljtech.istudybucket.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum PostType {
    QUESTION("question"),
    RESOURCE("resource");

    private String postType;
}
