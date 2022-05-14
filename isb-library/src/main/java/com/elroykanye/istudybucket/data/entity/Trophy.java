package com.elroykanye.istudybucket.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.Instant;
import java.time.LocalDateTime;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trophyId;

    @Column(name = "trophy_name", length = 32, unique = true)
    private String trophyName;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "awarded_date")
    private LocalDateTime awardedDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_user_id", nullable = false)
    private User user;

    //TODO add relationship to user
}
