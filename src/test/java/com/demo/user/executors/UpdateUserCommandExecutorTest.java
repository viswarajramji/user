package com.demo.user.executors;

import com.demo.user.command.UpdateUserCommand;
import com.demo.user.model.User;
import com.demo.user.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdateUserCommandExecutorTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UpdateUserCommandExecutor updateUserCommandExecutor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_UserFound() {
        // Arrange
        Long userId = 1L;
        User existingUser = new User(userId, "old_username", "old.email@example.com");
        UpdateUserCommand command = new UpdateUserCommand(userId, "new_username", "new.email@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        // Act
        User result = updateUserCommandExecutor.execute(command);

        // Assert
        assertNotNull(result);
        assertEquals("new_username", result.getUsername());
        assertEquals("new.email@example.com", result.getEmailId());

        verify(userRepository).findById(userId);
        verify(userRepository).save(existingUser);
    }

    @Test
    void testExecute_UserNotFound() {
        // Arrange
        Long userId = 1L;
        UpdateUserCommand command = new UpdateUserCommand(userId, "new_username", "new.email@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            updateUserCommandExecutor.execute(command);
        });

        assertEquals("User not found", exception.getMessage());

        verify(userRepository).findById(userId);
        verify(userRepository, never()).save(any(User.class));  // Ensure save is never called
    }
}

