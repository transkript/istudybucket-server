package com.feljtech.istudybucket.entity.relation;

import com.feljtech.istudybucket.entity.Post;
import com.feljtech.istudybucket.entity.User;
import com.feljtech.istudybucket.entity.composite.UserVotePostKey;
import com.feljtech.istudybucket.enums.VoteType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_vote_post")
public class Vote {
    @EmbeddedId
    private UserVotePostKey id;

    @Column(name = "vote")
    private VoteType vote;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    @MapsId("postId")
    private Post post;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(nullable = false)
    @MapsId("userId")
    private User user;

}
