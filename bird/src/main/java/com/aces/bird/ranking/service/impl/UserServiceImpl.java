package com.aces.bird.ranking.service.impl;

import com.aces.bird.ranking.model.dto.UserDto;
import com.aces.bird.ranking.model.entity.User;
import com.aces.bird.ranking.repository.UserRepository;
import com.aces.bird.ranking.service.UserService;
import com.aces.bird.ranking.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(UserDto userDto) {
        User newUser = new User(userDto.getName(), userDto.getEmail());
        return userRepository.save(newUser);
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with id %s not found", userId)));
    }

    @Override
    public User updateUser(String userId, UserDto userDto) {
        return null;
    }

    @Override
    public void deleteUserById(String userId) {

    }
}
