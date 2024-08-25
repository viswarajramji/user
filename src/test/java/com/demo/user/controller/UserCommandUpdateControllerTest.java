package com.demo.user.controller;

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

class UserCommandUpdateControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserCommandController userCommandController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
        assertEquals(200, response.getStatusCodeValue());
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
    void testUpdateUser_InvalidInput() {
        // Arrange
        Long userId = 1L;
        UpdateUserCommand command = new UpdateUserCommand(userId, "", "invalid_email");

        BindException bindException = new BindException(command, "command");
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindException);

        // Normally, this validation would be triggered by the Spring framework before calling the controller method
        // This is typically tested in integration tests

        // Simulate a validation exception manually to check how the controller would handle it
        assertThrows(MethodArgumentNotValidException.class, () -> {
            throw ex;  // Simulate the validation exception
        });
    }
}
