package com.elroykanye.istudybucket.data.entity;

import com.elroykanye.istudybucket.data.entity.relation.UserInBucket;
import com.elroykanye.istudybucket.data.entity.relation.UserInChat;
import com.elroykanye.istudybucket.data.entity.relation.UserLinkUser;
import com.elroykanye.istudybucket.data.enums.Gender;
import com.elroykanye.istudybucket.data.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Elroy Kanye
 *
 * Modified by: Elroy Kanye
 * Modified on: 12/05/2022
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

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
    private LocalDateTime dob;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 16)
    private Gender gender;

    // TODO verify enum type matches db
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole userRole;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "user_verified")
    private Boolean userVerified;

    // one to one relationship with Address
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "address_address_id")
    private Address address;

    // one to many relation with trophies
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Trophy> trophies;

    // relation to other users
    @OneToMany(mappedBy = "rightLinkUser", orphanRemoval = true)
    @ToString.Exclude
    private List<UserLinkUser> linkToUsers;

    @OneToMany(mappedBy = "leftLinkUser", orphanRemoval = true)
    @ToString.Exclude
    private List<UserLinkUser> linkOfUsers;

    @Builder.Default
    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Bucket> buckets = new ArrayList<>();

    // [special] many to many relationship with Bucket entity
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<UserInBucket> memberships;

    @Builder.Default
    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<UserInChat> userInChats = new LinkedHashSet<>();

}
