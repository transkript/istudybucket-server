package com.feljtech.istudybucket.entity;

import com.feljtech.istudybucket.entity.relation.UserVotePost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Elroy Kanye
 *
 * Modified By: ...
 * Modified Date: ...
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "upvotes")
    private Long upvotes;

    @Column(name = "downvotes")
    private Long downvotes;

    @Column(name = "comment_count")
    private Long commentCount;

    @Column(name = "post_type", length = 16)
    private String postType;

    // many to one relationship to User entity (author)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    // one to many relationship with Comment entity
    @OneToMany(mappedBy = "sourcePost", fetch = FetchType.LAZY)
    private Set<Comment> comments;
    
    @OneToMany(mappedBy = "post")
    private Set<UserVotePost> votes;

}
