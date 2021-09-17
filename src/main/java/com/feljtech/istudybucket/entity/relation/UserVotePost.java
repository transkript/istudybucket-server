package com.feljtech.istudybucket.entity.relation;

import com.feljtech.istudybucket.entity.Post;
import com.feljtech.istudybucket.entity.User;
import com.feljtech.istudybucket.entity.composite.UserVotePostKey;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserVotePost {
    @EmbeddedId
    private UserVotePostKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "vote")
    private byte vote;
}
