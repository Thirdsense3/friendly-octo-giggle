package com.aces.bird.ranking.service.mapper;

import com.aces.bird.ranking.model.dto.UserDto;
import com.aces.bird.ranking.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return user;
    }

    public UserDto toDto(User entity) {
        UserDto userDto = new UserDto();

        userDto.setUserId(entity.getUserId());
        userDto.setName(entity.getName());
        userDto.setEmail(entity.getEmail());
        return userDto;
    }

    public List<UserDto> toDtoList(List<User> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }

}
