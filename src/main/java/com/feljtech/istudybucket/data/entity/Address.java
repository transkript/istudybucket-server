package com.feljtech.istudybucket.data.entity;

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
@AllArgsConstructor
@RequiredArgsConstructor
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

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private User user;

}
