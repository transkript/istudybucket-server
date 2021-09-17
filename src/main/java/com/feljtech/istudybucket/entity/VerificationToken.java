package com.feljtech.istudybucket.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_id")
    private Long tokenId;

    @Column(name = "token_value")
    private String tokenValue;

    @Column(name = "expiry_date")
    private Instant expirtyDate;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userToken")
    @ToString.Exclude
    private User user;

}
