package com.feljtech.istudybucket.entity.relation;

import com.feljtech.istudybucket.entity.Bucket;
import com.feljtech.istudybucket.entity.User;
import com.feljtech.istudybucket.entity.composite.UserInBucketKey;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserInBucket {
    @EmbeddedId
    private UserInBucketKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("bucketId")
    @JoinColumn(name = "bucket_id")
    private Bucket bucket;

    @Column(name = "joined_date")
    private Date joinedDate;
}
