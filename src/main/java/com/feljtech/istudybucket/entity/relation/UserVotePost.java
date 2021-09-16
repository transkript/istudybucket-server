package com.feljtech.istudybucket.entity.relation;

import com.feljtech.istudybucket.entity.Post;
import com.feljtech.istudybucket.entity.User;
import com.feljtech.istudybucket.entity.composite.UserVotePostKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
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
