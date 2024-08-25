package com.demo.user.controller;

import com.demo.user.model.User;
import com.demo.user.query.GetAllUsersQuery;
import com.demo.user.query.GetUserByIdQuery;
import com.demo.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/api/query")
public class UserQueryController {

    private final UserService userService;

    @Autowired
    public UserQueryController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@Valid @PathVariable Long userId) {
        User result = userService.executeQuery(new GetUserByIdQuery(userId));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{userId}/email")
    public ResponseEntity<String> getUserEmailById(@Valid @PathVariable Long userId) {
        User result = userService.executeQuery(new GetUserByIdQuery(userId));
        return ResponseEntity.ok(result.getEmailId());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> result = userService.executeQuery(new GetAllUsersQuery());
        return ResponseEntity.ok(result);
    }
}
