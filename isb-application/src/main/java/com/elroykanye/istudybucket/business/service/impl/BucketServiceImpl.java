package com.elroykanye.istudybucket.business.service.impl;

import com.elroykanye.istudybucket.api.dto.BucketDto;
import com.elroykanye.istudybucket.api.dto.response.EntityResponse;
import com.elroykanye.istudybucket.business.mapper.BucketMapper;
import com.elroykanye.istudybucket.business.service.BucketService;
import com.elroykanye.istudybucket.business.service.UserService;
import com.elroykanye.istudybucket.data.entity.Bucket;
import com.elroykanye.istudybucket.data.entity.Chat;
import com.elroykanye.istudybucket.data.entity.User;
import com.elroykanye.istudybucket.data.entity.composite.UserInChatKey;
import com.elroykanye.istudybucket.data.entity.relation.UserInChat;
import com.elroykanye.istudybucket.data.enums.ChatType;
import com.elroykanye.istudybucket.data.repository.BucketRepository;
import com.elroykanye.istudybucket.data.repository.ChatRepository;
import com.elroykanye.istudybucket.data.repository.UserInChatRepository;
import com.elroykanye.istudybucket.excetion.EntityException;
import com.elroykanye.istudybucket.excetion.IStudyBucketException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {
    private final BucketRepository bucketRepository;
    private final BucketMapper bucketMapper;
    private final UserService userService;

    private final ChatRepository chatRepository;
    private final UserInChatRepository userInChatRepository;

    @Override
    @Transactional
    public EntityResponse createBucket(BucketDto bucketDto) {
        Bucket bucket = bucketMapper.mapDtoToBucket(bucketDto);
        User bucketCreator;
        Chat defaultChatRoom;
        {
            if (bucketDto.getCreatorId() == null) {
                log.error("creator id is required");
                throw new IStudyBucketException.IllegalArgumentException("Creator id is required", HttpStatus.BAD_REQUEST);
            }
            bucketCreator  = userService.getUserEntity(bucketDto.getCreatorId());
        }
        {
            if (bucketDto.getDefaultChatId() != null) {
                defaultChatRoom = chatRepository.findById(bucket.getDefaultChatId())
                        .orElseThrow(() -> new EntityException.EntityNotFoundException("chat", bucketDto.getDefaultChatId()));
                if (defaultChatRoom.getBucket() != null) {
                    throw new EntityException.EntityAlreadyExistsException("bucket chat", defaultChatRoom.getId());
                }
                if(!Objects.equals(defaultChatRoom.getCreator().getId(), bucketDto.getCreatorId())) {
                    throw new IStudyBucketException.IllegalStateException("chat creator is not the same as bucket creator", HttpStatus.BAD_REQUEST);
                }
            } else {
                defaultChatRoom = chatRepository.save(Chat.builder()
                        .bucket(null).createdAt(LocalDateTime.now()).creator(bucketCreator)
                        .title(bucketDto.getTitle()).description(bucketDto.getDescription())
                        .type(ChatType.GROUP_PRIVATE)
                        .build()
                );
            }
            bucket.getChatRooms().add(defaultChatRoom);
            bucket.setDefaultChatId(defaultChatRoom.getId());

        }


        bucket.setCreator(bucketCreator);
        bucket.setId(null);
        bucket.setCreatedAt(LocalDateTime.now()); bucket.setUpdatedAt(LocalDateTime.now());
        bucket = bucketRepository.save(bucket);

        // save the chat room
        defaultChatRoom.setBucket(bucket);
        chatRepository.save(defaultChatRoom);

        // save the relation between creator and chatroom
        userInChatRepository.save(UserInChat.builder().chat(defaultChatRoom).participant(bucketCreator).joinedAt(LocalDateTime.now())
                .userInChatKey(UserInChatKey.builder().userId(bucketCreator.getId()).chatId(defaultChatRoom.getId()).build())
                .build());
        log.info("Successfully added bucket: {}", bucketDto);
        return EntityResponse.builder().id(bucket.getId())
                .message("Bucket added successfully").entity("bucket").build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BucketDto> getAllBuckets() {
        return getBuckets().stream().map(bucketMapper::mapBucketToDto).collect(Collectors.toList());
    }

    @Override
    public BucketDto getBucketById(Long bucketId) {
        return bucketMapper.mapBucketToDto(getBucketEntity(bucketId));
    }

    @Override
    @Transactional
    public EntityResponse updateBucket(Long bucketId, BucketDto bucketDto) {
        if (bucketId == null || !bucketId.equals(bucketDto.getId())) {
            throw new IStudyBucketException.IllegalArgumentException("Bucket id for update not valid");
        }
        Bucket bucket = getBucketEntity(bucketId);
        {
            if (!Objects.equals(bucketDto.getTitle(), bucket.getTitle())) {
                bucket.setTitle(bucketDto.getTitle());
            }
            if(!Objects.equals(bucketDto.getDescription(), bucket.getDescription())) {
                bucket.setDescription(bucketDto.getDescription());
            }
            bucket.setUpdatedAt(LocalDateTime.now());
        }
        return EntityResponse.builder().id(bucketId).message("Updated bucket successfully").entity("bucket").build();
    }

    @Override
    @Transactional(readOnly = true)
    public Bucket getBucketEntity(Long id) {
        return bucketRepository.findById(id).orElseThrow(() -> {
            log.error("Bucket with id {} not found", id);
            throw new EntityException.EntityNotFoundException("bucket", id);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<Bucket> getBuckets() {
        return bucketRepository.findAll();
    }

    @Override
    @Transactional
    public Void deleteBucketById(Long bucketId) {
        bucketRepository.findById(bucketId).ifPresentOrElse(bucketRepository::delete, () -> {
            throw new EntityException.EntityNotFoundException("bucket", bucketId);
        });
        return null;
    }
}
