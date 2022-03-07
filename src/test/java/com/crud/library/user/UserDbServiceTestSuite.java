package com.crud.library.user;


import com.crud.library.domain.user.User;
import com.crud.library.domain.user.UserDto;
import com.crud.library.domain.user.UserNotFoundException;
import com.crud.library.repository.UserRepository;
import com.crud.library.service.UserDbService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserDbServiceTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDbService userDbService;

    @DisplayName("Test for saving user object")
    @Test
    void testSaveUser() {
        // Given
        User user = new User("name", "lastname", LocalDate.now());
        // When
        userRepository.save(user);
        // Then
        Long id = user.getUserId();
        Optional<User> userOptional = userRepository.findById(id);
        assertTrue(userOptional.isPresent());
        // CleanUp
        userRepository.deleteById(id);
    }

    @DisplayName("Test for deleting user object")
    @Test
    void deleteUser() {
        // Given
        User user = new User("name", "lastname", LocalDate.now());
        // When
        userRepository.save(user);
        // Then
        Long id = user.getUserId();
        userDbService.deleteUser(id);
        assertEquals(0, userDbService.getAllUsers().size());
    }

    @DisplayName("Test for getting user object")
    @Test
    void getUserById() throws UserNotFoundException {
        // Given
        User user = new User("name", "lastname", LocalDate.now());
        // When
        userRepository.save(user);
        // Then
        Long id = user.getUserId();
        UserDto userId = userDbService.getUser(id);
        assertEquals("name", userId.getFirstName());
        // CleanUp
        userRepository.deleteById(id);

    }

    @DisplayName("Test for getting all user objects")
    @Test
    void getAllUsers() {
        // Given
        User user = new User("user", "lastname", LocalDate.now());
        User user1 = new User("user2", "lastname2", LocalDate.now());
        User user2 = new User("user3", "lastname3", LocalDate.now());
        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);
        // When
        List<UserDto> userList = userDbService.getAllUsers();
        Long firstUser = user.getUserId();
        Long secondUser = user1.getUserId();
        Long thirdUser = user2.getUserId();
        // Then
        assertEquals(3, userList.size());
        // Then
        userRepository.deleteById(firstUser);
        userRepository.deleteById(secondUser);
        userRepository.deleteById(thirdUser);
    }
}
