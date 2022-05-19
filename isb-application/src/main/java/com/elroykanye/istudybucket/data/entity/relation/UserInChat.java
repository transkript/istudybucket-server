package com.elroykanye.istudybucket.data.entity.relation;

import com.elroykanye.istudybucket.data.entity.Chat;
import com.elroykanye.istudybucket.data.entity.User;
import com.elroykanye.istudybucket.data.entity.composite.UserInChatKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserInChat {
    @EmbeddedId
    private UserInChatKey userInChatKey;

    @MapsId("chatId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "chat_chat_id", nullable = false)
    private Chat chat;

    @MapsId("userId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "participant_user_id", nullable = false)
    private User participant;

    @Column(name = "joined_at")
    private LocalDateTime joinedAt;
}
