package com.crud.library.service;


import com.crud.library.domain.user.User;
import com.crud.library.domain.user.UserDto;
import com.crud.library.domain.user.UserNotFoundException;
import com.crud.library.mapper.UserMapper;
import com.crud.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDbService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserMapper mapper;

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return mapper.mapToUserDtoList(users);
    }

    public User saveUser(final UserDto userDto) {
        User user = mapper.mapToUser(userDto);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto getUser(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        return mapper.mapToUserDto(user.orElseThrow(UserNotFoundException::new));
    }
    public UserDto updateUser(final UserDto userDto) {
        User savedUser = saveUser(userDto);
        return mapper.mapToUserDto(savedUser);
    }

}
