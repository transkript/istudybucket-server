package com.elroykanye.istudybucket.data.entity.composite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserInChatKey implements Serializable {
    @Column(name = "chat_chat_id")
    private Long chatId;

    @Column(name = "participant_user_id")
    private Long userId;
}
