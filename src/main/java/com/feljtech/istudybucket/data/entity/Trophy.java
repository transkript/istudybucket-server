package com.feljtech.istudybucket.entity;

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
public class Trophy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long trophyId;

    @Column(name = "trophy_name", length = 32, unique = true)
    private String trophyName;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "awarded_date")
    private Instant awardedDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_user_id", nullable = false)
    private User user;

    //TODO add relationship to user
}
