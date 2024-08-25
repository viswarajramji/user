package com.demo.user.executors;

import com.demo.user.api.Event;
import com.demo.user.command.DeleteUserCommand;
import com.demo.user.event.DeleteUserEvent;
import com.demo.user.kafka.KafkaProducer;
import com.demo.user.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteUserCommandExecutorTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private KafkaProducer kafkaProducer;

    @InjectMocks
    private DeleteUserCommandExecutor deleteUserCommandExecutor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() {
        // Arrange
        Long userId = 1L;
        DeleteUserCommand command = new DeleteUserCommand(userId);

        // Act
        deleteUserCommandExecutor.execute(command);

        // Assert
        verify(userRepository).deleteById(userId);
        verify(kafkaProducer).sendEvent(any(DeleteUserEvent.class));
    }

    @Test
    void testCreateEvent() {
        // Arrange
        Long userId = 1L;

        // Act
        Event event = deleteUserCommandExecutor.createEvent(userId);

        // Assert
        assertNotNull(event);
        assertTrue(event instanceof DeleteUserEvent);
        assertEquals(userId, ((DeleteUserEvent) event).getUserId());
    }
}
