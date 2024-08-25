package com.demo.user.executors;

import com.demo.user.model.User;
import com.demo.user.query.GetUserByIdQuery;
import com.demo.user.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetUserByIdCommandExecutorTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUserByIdCommandExecutor getUserByIdCommandExecutor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_UserFound() {
        // Arrange
        Long userId = 1L;
        User user = new User(userId, "john_doe", "john.doe@example.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        User result = getUserByIdCommandExecutor.execute(new GetUserByIdQuery(userId));

        // Assert
        assertNotNull(result);
        assertEquals("john_doe", result.getUsername());
        assertEquals("john.doe@example.com", result.getEmailId());

        verify(userRepository).findById(userId);
    }

    @Test
    void testExecute_UserNotFound() {
        // Arrange
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            getUserByIdCommandExecutor.execute(new GetUserByIdQuery(userId));
        });

        assertEquals("User not found", exception.getMessage());

        verify(userRepository).findById(userId);
    }
}

