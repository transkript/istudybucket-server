package com.feljtech.istudybucket.data.entity;

import com.feljtech.istudybucket.data.enums.VoteType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long voteId;

    @Column(name = "vote")
    private VoteType vote;

    @ManyToOne(optional = false)
    @JoinColumn(name = "post_post_id", nullable = false)
    private Post post;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "user_user_id", nullable = false)
    private User user;

}
