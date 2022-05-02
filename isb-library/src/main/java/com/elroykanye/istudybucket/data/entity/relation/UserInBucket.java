package com.elroykanye.istudybucket.data.entity.relation;

import com.elroykanye.istudybucket.data.entity.composite.UserInBucketKey;
import com.elroykanye.istudybucket.data.entity.Bucket;
import com.elroykanye.istudybucket.data.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
