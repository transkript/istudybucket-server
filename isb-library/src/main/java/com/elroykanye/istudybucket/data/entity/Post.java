package com.elroykanye.istudybucket.data.entity;

import com.elroykanye.istudybucket.data.enums.PostType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.ArrayList;
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

    @Column(name = "created_date")
    private Instant createdDate;

    // many to one relationship to User entity (author)
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_user_id", nullable = false)
    private User author;

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    @ToString.Exclude
    private List<Vote> votes;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "source_post_post_id", unique = true)
    private Post sourcePost;

    @OneToMany(mappedBy = "sourcePost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> comments = new ArrayList<>();


}
