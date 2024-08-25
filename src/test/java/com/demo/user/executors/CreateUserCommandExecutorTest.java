package com.demo.user.executors;
import com.demo.user.command.CreateUserCommand;
import com.demo.user.model.User;
import com.demo.user.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateUserCommandExecutorTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUserCommandExecutor createUserCommandExecutor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_UserDoesNotExist() {
        // Arrange
        CreateUserCommand command = new CreateUserCommand("john_doe", "john.doe@example.com");
        User user = new User(null, command.getUsername(), command.getEmailId());

        when(userRepository.findByEmailId(command.getEmailId())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        User result = createUserCommandExecutor.execute(command);

        // Assert
        assertNotNull(result);
        assertEquals(command.getUsername(), result.getUsername());
        assertEquals(command.getEmailId(), result.getEmailId());

        verify(userRepository).findByEmailId(command.getEmailId());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testExecute_UserAlreadyExists() {
        // Arrange
        CreateUserCommand command = new CreateUserCommand("john_doe", "john.doe@example.com");
        User existingUser = new User(1L, "john_doe", "john.doe@example.com");

        when(userRepository.findByEmailId(command.getEmailId())).thenReturn(Optional.of(existingUser));

        // Act & Assert
        assertThrows(DataIntegrityViolationException.class, () -> {
            createUserCommandExecutor.execute(command);
        });

        verify(userRepository).findByEmailId(command.getEmailId());
        verify(userRepository, never()).save(any(User.class));  // Ensure save is never called
    }
}
