package com.feljtech.istudybucket.entity.composite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class UserInBucketKey implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "bucket_id")
    private Long bucketId;
}
