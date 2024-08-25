package com.demo.user.controller;

import com.demo.user.model.User;
import com.demo.user.query.GetAllUsersQuery;
import com.demo.user.query.GetUserByIdQuery;
import com.demo.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserQueryControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserQueryController userQueryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() {
        // Arrange
        User user = new User(1L, "john_doe", "john.doe@example.com");
        when(userService.executeQuery(any(GetUserByIdQuery.class))).thenReturn(user);

        // Act
        ResponseEntity<User> response = userQueryController.getUserById(1L);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());

    }

    @Test
    void testGetUserEmailById() {
        // Arrange
        User user = new User(1L, "john_doe", "john.doe@example.com");
        when(userService.executeQuery(any(GetUserByIdQuery.class))).thenReturn(user);

        // Act
        ResponseEntity<String> response = userQueryController.getUserEmailById(1L);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        User user = new User(1L, "john_doe", "john.doe@example.com");
        List<User> users = Collections.singletonList(user);
        when(userService.executeQuery(any(GetAllUsersQuery.class))).thenReturn(users);

        // Act
        ResponseEntity<List<User>> response = userQueryController.getAllUsers();

        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get(0));
    }
}
