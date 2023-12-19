package com.azeem.socialmedia.controller;

import com.azeem.socialmedia.converter.UserRequestDtoToUser;
import com.azeem.socialmedia.converter.UserToUserResponseDto;
import com.azeem.socialmedia.domain.User;
import com.azeem.socialmedia.dto.request.UserRequestDto;
import com.azeem.socialmedia.dto.response.UserResponseDto;
import com.azeem.socialmedia.service.UserService;
import com.azeem.socialmedia.system.Result;
import com.azeem.socialmedia.system.StatusCode;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserRequestDtoToUser userRequestDtoToUser;
    private final UserToUserResponseDto userToUserResponseDto;

    public UserController(UserService userService, UserRequestDtoToUser userRequestDtoToUser, UserToUserResponseDto userToUserResponseDto) {
        this.userService = userService;
        this.userRequestDtoToUser = userRequestDtoToUser;
        this.userToUserResponseDto = userToUserResponseDto;
    }


    @PostMapping
    public Result addUser(@RequestBody UserRequestDto userRequestDto) {
        User user = userRequestDtoToUser.convert(userRequestDto);
        User savedUser = userService.save(user);
        UserResponseDto savedUserResponseDto = userToUserResponseDto.convert(savedUser);
        return new Result(true, StatusCode.SUCCESS, "message - addUser", savedUserResponseDto);
    }

    @GetMapping("/{userId}")
    public Result getUserById(@PathVariable String userId) {
        User user = userService.findById(userId);
        UserResponseDto userResponseDto = userToUserResponseDto.convert(user);
        return new Result(true, StatusCode.SUCCESS, "message - getUserById", userResponseDto);
    }

    @PutMapping("/{userId}")
    public Result updateUser(@PathVariable String userId, @RequestBody UserRequestDto userRequestDto) {
        User user = userRequestDtoToUser.convert(userRequestDto);
        User updatedUser = userService.update(userId, user);
        UserResponseDto updatedUserResponseDto = userToUserResponseDto.convert(updatedUser);
        return new Result(true, StatusCode.SUCCESS, "message - updateUser", updatedUserResponseDto);
    }

    @DeleteMapping("/{userId}")
    public Result deleteUser(@PathVariable String userId) {
        userService.delete(userId);
        return new Result(true, StatusCode.SUCCESS, "message - deleteUser");
    }

    @GetMapping
    public Result getAllUser() {
        List<UserResponseDto> allUsers = userService.findAll().stream().map(userToUserResponseDto::convert).toList();

        // todo
        return new Result(true, StatusCode.SUCCESS, "message - getAllUser", allUsers);
    }
}
