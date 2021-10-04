package com.feljtech.istudybucket.data.entity;


import lombok.*;

import javax.persistence.*;
import java.time.Instant;

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

    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "content")
    private String content;

    // many to one relationship with User entity
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User author;

    // many to one relationship with Post
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    @ToString.Exclude
    private Post sourcePost;
}
