package com.feljtech.istudybucket.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
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

    @Column(name = "username")
    private String username;

    @Column(name = "user_email")
    private String userEmail;

}
