package com.aces.bird.ranking.service.impl;

import com.aces.bird.ranking.model.dto.UserDto;
import com.aces.bird.ranking.model.entity.User;
import com.aces.bird.ranking.repository.UserRepository;
import com.aces.bird.ranking.service.UserService;
import com.aces.bird.ranking.service.exception.ResourceNotFoundException;
import com.aces.bird.ranking.service.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        User newUser = UserMapper.toEntity(userDto);
        User savedUser = userRepository.save(newUser);
        return userMapper.toDto(savedUser);
    }

    @Override
    @Transactional
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with id %s not found", userId)));

        return userMapper.toDto(user);

    }

    @Override
    @Transactional
    public UserDto updateUser(String userId, UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }

        User user = userOptional.get();

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setHighScore(user.getHighScore());

        userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public void deleteUserById(String userId) throws ResourceNotFoundException {
        User userToDelete = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        userRepository.delete(userToDelete);
    }
}
