package com.elroykanye.istudybucket.data.entity.relation;

import com.elroykanye.istudybucket.data.entity.composite.UserLinkUserKey;
import com.elroykanye.istudybucket.data.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

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
    private LocalDateTime linkDate;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId("rightLinkUserId")
    private User rightLinkUser;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId("leftLinkUserId")
    private User leftLinkUser;

}
