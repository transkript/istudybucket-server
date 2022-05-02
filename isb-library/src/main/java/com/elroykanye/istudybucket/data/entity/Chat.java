package com.elroykanye.istudybucket.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

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
@Builder
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "chat_title", length = 32)
    private String chatTitle;

    @Column(name = "chat_type", length = 32)
    private String chatType;

    @Column(name = "chat_description", length = 32)
    private String chatDescription;

    @Column(name = "created_at")
    private Instant creationDate;

    // one to many relationship with Message entity
    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Message> messages;

    // one to one relationship with Bucket entity
    @OneToOne(mappedBy = "chatRoom")
    private Bucket bucket;
}
