package com.feljtech.istudybucket.data.entity;

import com.feljtech.istudybucket.data.enums.PostType;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

/**
 * @author Elroy Kanye
 *
 * Modified By: ...
 * Modified Date: ...
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @Column(name = "post_title", length = 32)
    private String postTitle;

    @Column(name = "content", length = 512)
    private String content;

    @Column(name = "post_type", length = 16)
    private PostType postType;

    @Column(name = "creation_date")
    private Instant creationDate;

    // many to one relationship to User entity (author)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User author;

    // one to many relationship with Comment entity
    @OneToMany(mappedBy = "sourcePost", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    @ToString.Exclude
    private List<Vote> votes;


}
