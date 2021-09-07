package com.feljtech.istudybucket.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum VoteType {
    UPVOTE(1),
    DOWNVOTE(-1);

    private int vote;
}
