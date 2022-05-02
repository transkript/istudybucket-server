package com.elroykanye.istudybucket.data.entity.composite;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class UserLinkUserKey implements Serializable {
    @Column(name = "left_link")
    private Long leftLinkUserId;

    @Column(name = "right_link")
    private Long rightLinkUserId;
}
