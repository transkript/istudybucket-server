package com.feljtech.istudybucket.data.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum PostType {
    QUESTION("QUESTION"),
    RESOURCE("RESOURCE");

    private String postType;
}
