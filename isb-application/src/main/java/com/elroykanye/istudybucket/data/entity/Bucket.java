package com.elroykanye.istudybucket.data.entity;

import com.elroykanye.istudybucket.data.entity.relation.UserInBucket;
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
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Andrew Tatah
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
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bucket_id")
    private Long id;

    @Column(name = "bucket_title", length = 32, unique = true)
    private String title;

    @Column(name = "description", length = 128)
    private String description;

    @Column(name = "group_image")
    private String groupImage;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    // one to one relationship with Chat entity
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bucket_id", referencedColumnName = "chat_id")
    private Chat chatRoom;

    // [special] many to many relation with User entity
    @OneToMany(mappedBy = "bucket")
    @ToString.Exclude
    private List<UserInBucket> memberships;

    @ManyToOne(optional = false)
    @JoinColumn(name = "creator_user_id", nullable = false)
    private User creator;

}
