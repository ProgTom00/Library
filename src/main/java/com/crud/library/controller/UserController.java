package com.crud.library.controller;

import com.crud.library.domain.user.UserDto;
import com.crud.library.domain.user.UserNotFoundException;
import com.crud.library.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/library")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserDbService userDbService;


    @GetMapping(value = "getUsers")
    public List<UserDto> getUsers() {
        return userDbService.getAllUsers();
    }

    @GetMapping(value = "getUser")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException {
        return userDbService.getUser(userId);

    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(@RequestParam Long userId) {
        userDbService.deleteUser(userId);

    }

    @PutMapping(value = "updateUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userDbService.updateUser(userDto);
    }

    @PostMapping(value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        userDbService.saveUser(userDto);

    }
}
