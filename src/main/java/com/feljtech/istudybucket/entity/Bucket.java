package com.feljtech.istudybucket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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
    private Long bucketId;

    @Column(name = "bucket_title", length = 32)
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

}
