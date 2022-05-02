package com.elroykanye.istudybucket.data.entity.composite;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class UserInBucketKey implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "bucket_id")
    private Long bucketId;
}
