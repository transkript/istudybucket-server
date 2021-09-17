package com.feljtech.istudybucket.entity.composite;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class UserVotePostKey implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "post_id")
    private Long postId;
}
