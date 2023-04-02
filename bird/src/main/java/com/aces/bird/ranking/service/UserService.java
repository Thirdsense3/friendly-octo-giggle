package com.aces.bird.ranking.service;

import com.aces.bird.ranking.model.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();
    UserDto createUser(UserDto userDto);
    UserDto getUserById(String id);
    UserDto updateUser(String id, UserDto userDto);
    void deleteUserById(String id);

}
