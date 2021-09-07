package com.feljtech.istudybucket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Andrew Tatah
 *
 * Modified By: Elroy Kanye
 * Modified Date: 07-09-2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long chatId;

    @Column(name = "chat_title", length = 32)
    private String chatTitle;

    @Column(name = "chat_type", length = 32)
    private String chatType;

    // one to many relationship with Message entity
    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Message> messages;

    // one to one relationship with Bucket entity
    @OneToOne(mappedBy = "chatRoom")
    private Bucket bucket;
}
