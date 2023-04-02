package com.aces.bird.ranking.service;

import com.aces.bird.ranking.model.dto.UserDto;
import com.aces.bird.ranking.model.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User createUser(UserDto userDto);
    User getUserById(String id);
    User updateUser(String id, UserDto userDto);
    void deleteUser(String id);

}
