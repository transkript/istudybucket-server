package com.feljtech.istudybucket.entity;

import com.feljtech.istudybucket.entity.relation.UserInBucket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author Andrew Tatah
 *
 * Modified By: Elroy Kanye
 * Modified Date: 07-09-2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bucket_id")
    private Long bucketId;

    @Column(name = "bucket_title", length = 32, unique = true)
    private String bucketTitle;

    @Column(name = "description", length = 128)
    private String description;

    @Column(name = "creator_name", length = 32)
    private String creatorName;

    @Column(name = "participants")
    private Long participants;

    @Column(name = "group_image")
    private String groupImage;

    @Column(name = "creation_date")
    private Date creationDate;

    // one to one relationship with Chat entity
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_id", referencedColumnName = "bucket_id")
    private Chat chatRoom;

    // [special] many to many relation with User entity
    @OneToMany(mappedBy = "bucket")
    private Set<UserInBucket> memberships;
}
