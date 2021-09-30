package com.feljtech.istudybucket.entity;

import com.feljtech.istudybucket.entity.relation.UserInBucket;
import com.feljtech.istudybucket.entity.relation.Vote;
import com.feljtech.istudybucket.enums.UserRole;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * @author Elroy Kanye
 *
 * Modified by: ...
 * Modified on: ...
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", length = 32, unique = true)
    private String username;

    @Column(name = "email", length = 32, unique = true)
    private String email;

    @Column(name = "password", length = 128)
    private String password;

    @Column(name = "first_name", length = 32)
    private String firstName;

    @Column(name = "last_name", length = 32)
    private String lastName;

    @Column(name = "phone_number", length = 32)
    private String phoneNumber;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "gender", length = 16)
    private String gender;

    // TODO verify enum type matches db
    @Column(name = "user_role")
    private UserRole userRole;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "user_verified")
    private Boolean userVerified;

    // one to one relationship with Address
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "address_id")
    private Address address;

    // one to many relationship with Post entity
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Post> posts;

    // one to many relationship with Comment entity
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Comment> comments;

    // [special] many to many relationship with Bucket entity
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<UserInBucket> memberships;

    // one to one relation with a post vote
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Vote vote;

}
