package com.feljtech.istudybucket.entity;

import lombok.*;

import javax.persistence.*;

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

    //TODO add relationship to user
}
