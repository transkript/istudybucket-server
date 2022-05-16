package com.elroykanye.istudybucket.business.service.impl;

import com.elroykanye.istudybucket.api.dto.ChatDto;
import com.elroykanye.istudybucket.api.dto.response.EntityResponse;
import com.elroykanye.istudybucket.business.mapper.ChatMapper;
import com.elroykanye.istudybucket.business.service.BucketService;
import com.elroykanye.istudybucket.business.service.ChatService;
import com.elroykanye.istudybucket.business.service.UserService;
import com.elroykanye.istudybucket.data.entity.Bucket;
import com.elroykanye.istudybucket.data.entity.Chat;
import com.elroykanye.istudybucket.data.entity.User;
import com.elroykanye.istudybucket.data.entity.composite.UserInChatKey;
import com.elroykanye.istudybucket.data.entity.relation.UserInChat;
import com.elroykanye.istudybucket.data.enums.ChatType;
import com.elroykanye.istudybucket.data.repository.ChatRepository;
import com.elroykanye.istudybucket.data.repository.UserInChatRepository;
import com.elroykanye.istudybucket.excetion.EntityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final UserInChatRepository userInChatRepository;
    private final ChatMapper chatMapper;
    private final UserService userService;
    private final BucketService bucketService;

    @Override
    public EntityResponse addChat(ChatDto chatDto) {
        Chat chat = chatMapper.mapDtoToChat(chatDto);
        User creator = userService.getUser(chatDto.getCreatorId());
        Bucket bucket = null;

        if (chatDto.getBucketId() != null) {
            try {
                bucket = bucketService.getBucket(chatDto.getBucketId());
            } catch (Exception e) {
                log.error("Bucket for creating chat with id {} not found", chatDto.getId());
            }
        }

        if(chat.getType() == ChatType.ONE_ON_ONE) {
            if(chatDto.getParticipants().size() != 1 || chatDto.getParticipants().contains(creator.getId())) {
                // TODO add proper exception handling
                throw new IllegalArgumentException("Chat must have 2 participants");
            }
            Long[] participants = new Long[]{chatDto.getParticipants().get(0), chatDto.getCreatorId()};
            for (Long participant : participants) {
                User user = userService.getUser(participant);
                Set<Chat> existingChats = chatRepository.findAllByCreator(user).stream().filter(c -> c.getUsersInChat().stream()
                                // retrieve all participants in all chats created by this creator
                                .map(UserInChat::getParticipant)
                                // retrieve all the user ids and check if this participant is part of the chats
                                .map(User::getId).collect(Collectors.toSet()).contains(chatDto.getParticipants().get(0)))
                        .collect(Collectors.toSet());

                if (existingChats.size() > 0) {
                    return EntityResponse.builder().id(existingChats.stream().findFirst().get().getId())
                            .message("Chat already exists").build();
                }
            }
        } else if (chat.getType() == ChatType.GROUP_PUBLIC || chat.getType() == ChatType.GROUP_PRIVATE) {
            if(chatDto.getParticipants().size() < 2) {
                // TODO add proper exception handling
                throw new IllegalArgumentException("Chat must have at least 2 participants");
            }
        }

        chat.setCreator(creator);
        chat.setBucket(bucket);
        Chat savedChat = chatRepository.save(chat);
        System.out.println(savedChat);

        chatDto.getParticipants().add(chatDto.getCreatorId());
        chatDto.getParticipants().forEach(userId -> {
            UserInChat userInChat = UserInChat.builder().chat(savedChat).participant(
                    userService.getUser(userId)
            ).userInChatKey(UserInChatKey.builder().chatId(savedChat.getId()).userId(userId).build()).build();
            System.out.println(userInChat);
            userInChatRepository.save(userInChat);
        });

        return EntityResponse.builder().id(savedChat.getId()).message("Chat creared successfully").build();
    }

    @Override
    public List<ChatDto> getChats() {
        return chatRepository.findAll().stream().map(chatMapper::mapChatToDto).collect(Collectors.toList());
    }

    @Override
    public ChatDto getChat(Long id) {
        return chatMapper.mapChatToDto(getChatById(id));
    }

    @Override
    public EntityResponse deleteChat(Long id) {
        if(!chatRepository.existsById(id)) {
            throw new EntityException.EntityNotFoundException("chat", id);
        }
        chatRepository.deleteById(id);
        return EntityResponse.builder().id(id).message("Chat deleted successfully").build();
    }

    @Override
    public EntityResponse updateChat(Long id, ChatDto chatDto) {
        if(!chatRepository.existsById(id)) {
            throw new EntityException.EntityNotFoundException("chat", id);
        }
        Chat chat = chatRepository.getById(id);
        Chat chatToUpdate = chatMapper.mapDtoToChat(chatDto);
        if (chatToUpdate.getTitle() != null && !chatToUpdate.getTitle().equals(chat.getTitle())) {
            chat.setTitle(chatDto.getTitle());
        }
        if (chatToUpdate.getType() != null && !chatToUpdate.getType().equals(chat.getType())) {
            chat.setType(chatToUpdate.getType());
        }
        if (chatToUpdate.getDescription() != null && !chatToUpdate.getDescription().equals(chat.getDescription())) {
            chat.setDescription(chatToUpdate.getDescription());
        }
        chatRepository.save(chat);
        return EntityResponse.builder().id(id).message("Chat updated successfully").build();
    }

    @Override
    public Chat getChatById(Long id) {
        if (id == null ) {
            throw new IllegalArgumentException("Chat id cannot be null");
        }
        return chatRepository.findById(id).orElseThrow(() -> {
            throw new EntityException.EntityNotFoundException("chat", id);
        });
    }
}
