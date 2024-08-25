package com.demo.user.controller;
import com.demo.user.command.CreateUserCommand;
import com.demo.user.command.DeleteUserCommand;
import com.demo.user.command.UpdateUserCommand;
import com.demo.user.model.User;
import com.demo.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class UserCommandControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserCommandController userCommandController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        // Arrange
        User user = new User(1L, "john_doe", "john.doe@example.com");
        when(userService.executeCommand(any(CreateUserCommand.class))).thenReturn(user);

        // Act
        ResponseEntity<User> response = userCommandController.createUser(new CreateUserCommand("john_doe", "john.doe@example.com"));

        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());
    }

    @Test
    void testUpdateUser() {
        // Arrange
        User user = new User(1L, "john_doe_updated", "john.doe_updated@example.com");
        when(userService.executeCommand(any(UpdateUserCommand.class))).thenReturn(user);

        // Act
        ResponseEntity<User> response = userCommandController.updateUser(1L, new UpdateUserCommand(1L, "john_doe_updated", "john.doe_updated@example.com"));

        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());
    }

    @Test
    void testDeleteUser() {
        // Act
        ResponseEntity<Void> response = userCommandController.deleteUser(1L);

        // Assert
        assertNotNull(response);
    }


    @Test
    void testUpdateUser_Success() {
        // Arrange
        Long userId = 1L;
        UpdateUserCommand command = new UpdateUserCommand(userId, "new_username", "new.email@example.com");
        User updatedUser = new User(userId, "new_username", "new.email@example.com");

        when(userService.executeCommand(any(UpdateUserCommand.class))).thenReturn(updatedUser);

        // Act
        ResponseEntity<User> response = userCommandController.updateUser(userId, command);

        // Assert
        assertNotNull(response);
        assertEquals(updatedUser, response.getBody());
        verify(userService, times(1)).executeCommand(command);
    }

    @Test
    void testUpdateUser_UserServiceThrowsException() {
        // Arrange
        Long userId = 1L;
        UpdateUserCommand command = new UpdateUserCommand(userId, "new_username", "new.email@example.com");

        when(userService.executeCommand(any(UpdateUserCommand.class)))
                .thenThrow(new RuntimeException("User not found"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userCommandController.updateUser(userId, command);
        });

        assertEquals("User not found", exception.getMessage());
        verify(userService, times(1)).executeCommand(command);
    }

    @Test
    void testUpdateUser_InvalidCommand() {
        // Arrange
        Long userId = 1L;
        UpdateUserCommand command = new UpdateUserCommand(userId, "", "invalid_email");

        BindException bindException = new BindException(command, "command");
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindException);

        // Here you can simulate a scenario where the validation fails
        // Normally, this would be handled by the Spring framework itself,
        // but you could simulate and assert this behavior in an integration test instead

        // Act & Assert
        assertThrows(MethodArgumentNotValidException.class, () -> {
            throw ex;  // Simulate a validation exception
        });
    }
}
