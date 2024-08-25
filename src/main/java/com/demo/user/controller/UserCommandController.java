package com.demo.user.controller;

import com.demo.user.command.*;
import com.demo.user.model.User;
import com.demo.user.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users/api/command")
public class UserCommandController {

    private final UserService userService;

    @Autowired
    public UserCommandController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid  @RequestBody CreateUserCommand command) {
        User result = userService.executeCommand(command);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@NonNull @PathVariable Long userId, @Valid @RequestBody UpdateUserCommand command) {
        command.setUserId(userId);
        User result = userService.executeCommand(command);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@NotNull @PathVariable Long userId) {
        userService.executeCommand(new DeleteUserCommand(userId));
        return ResponseEntity.ok().build();
    }
}
