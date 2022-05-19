package com.elroykanye.istudybucket.data.entity.relation;

import com.elroykanye.istudybucket.data.entity.User;
import com.elroykanye.istudybucket.data.entity.composite.UserLinkUserKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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

    @ManyToOne( optional = false)
    @MapsId("rightLinkUserId")
    private User rightLinkUser;

    @ManyToOne(optional = false)
    @MapsId("leftLinkUserId")
    private User leftLinkUser;

}
