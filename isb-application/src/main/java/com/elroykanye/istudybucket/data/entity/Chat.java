package com.elroykanye.istudybucket.data.entity;

import com.elroykanye.istudybucket.data.entity.relation.UserInChat;
import com.elroykanye.istudybucket.data.enums.ChatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 32)
    private String title;

    @Enumerated
    @Column(name = "type", nullable = false)
    private ChatType type;

    @Column(name = "description", length = 128)
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // many-to-one relationship with User entity
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "creator_user_id", nullable = false)
    private User creator;

    // one-to-many relationship with Message entity
    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ChatMessage> chatMessages = new ArrayList<>();

    // one-to-one relationship with Bucket entity
    @OneToOne(mappedBy = "chatRoom")
    private Bucket bucket;

    @Builder.Default
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<UserInChat> usersInChat = new LinkedHashSet<>();



}
