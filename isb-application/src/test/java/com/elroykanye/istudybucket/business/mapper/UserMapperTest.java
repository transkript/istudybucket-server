package com.elroykanye.istudybucket.business.mapper;

import com.elroykanye.istudybucket.api.dto.UserDto;
import com.elroykanye.istudybucket.config.constant.DefaultDto;
import com.elroykanye.istudybucket.config.constant.DefaultEntity;
import com.elroykanye.istudybucket.data.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserMapperTest {
    private final UserMapper userMapper;

    private User user;
    private UserDto userDto;

    @Autowired
    public UserMapperTest(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @BeforeEach
    void setUp() {
        user = DefaultEntity.DEFAULT_USER;
        userDto = DefaultDto.DEFAULT_USER_DTO;
    }

    @Test
    void mapUserToDto() {
        UserDto userDto1 = userMapper.mapUserToDto(user);

        assertEquals(user.getId(), userDto1.getId());
        assertEquals(user.getUsername(), userDto1.getUsername());
        assertEquals(user.getFirstName(), userDto1.getFirstName());
        assertEquals(user.getLastName(), userDto1.getLastName());
        assertEquals(user.getEmail(), userDto1.getEmail());
        assertEquals(user.getPhoneNumber(), userDto1.getPhoneNumber());
        assertEquals(user.getDob(), userDto1.getDob());
        assertEquals(user.getGender().name(), userDto1.getGender());

        assertEquals(user.getCreatedDate(), userDto1.getCreatedDate());
        assertEquals(user.getUserVerified(), userDto1.getUserVerified());
    }

    @Test
    void mapDtoToUser() {
        User user1 = userMapper.mapDtoToUser(userDto);

        assertEquals(userDto.getId(), user1.getId());
        assertEquals(userDto.getUsername(), user1.getUsername());
        assertEquals(userDto.getFirstName(), user1.getFirstName());
        assertEquals(userDto.getLastName(), user1.getLastName());
        assertEquals(userDto.getEmail(), user1.getEmail());
        assertEquals(userDto.getPhoneNumber(), user1.getPhoneNumber());
        assertEquals(userDto.getDob(), user1.getDob());
        assertEquals(userDto.getGender(), user1.getGender().name());

        assertEquals(userDto.getCreatedDate(), user1.getCreatedDate());
        assertEquals(userDto.getUserVerified(), user1.getUserVerified());
    }
}
