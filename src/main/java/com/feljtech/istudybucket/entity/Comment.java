package com.feljtech.istudybucket.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author Abongwa Bonalais
 *
 * Modified By: Elroy Kanye
 * Modified Date: 07-09-2021
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @Column(name = "author_name", length = 32)
    private String authorName;

    @Column(name = "creation_date")
    private Date creationDate;

    // many to one relationship with User entity
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User commentAuthor;

    // many to one relationship with Post
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    @ToString.Exclude
    private Post sourcePost;

    @OneToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Comment> comments;
}
