package com.feljtech.istudybucket.entity;

import lombok.*;

import javax.persistence.*;

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
@NoArgsConstructor
@Builder
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;

    @Column(name = "street", length = 32)
    private String street;

    @Column(name = "town", length = 32)
    private String town;

    @Column(name = "country", length = 32)
    private String country;

    @OneToOne(mappedBy = "address")
    private User user;
}
