package com.feljtech.istudybucket.data.entity.relation;

import com.feljtech.istudybucket.data.entity.User;
import com.feljtech.istudybucket.data.entity.composite.UserLinkUserKey;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserLinkUser {
    @EmbeddedId
    private UserLinkUserKey id;

    @Column(name = "link_date")
    private Instant linkDate;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId("rightLinkUserId")
    private User rightLinkUser;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId("leftLinkUserId")
    private User leftLinkUser;

}
