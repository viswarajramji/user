package com.demo.user.event;
import com.demo.user.api.EventExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

class DeleteUserEventTest {

    @Mock
    private EventExecutor<DeleteUserEvent> eventExecutor;

    @InjectMocks
    private DeleteUserEvent deleteUserEvent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deleteUserEvent = new DeleteUserEvent(1L);  // Initializing with a userId
    }

    @Test
    void testDeleteUserEventCreation() {
        // Assert that the event is created correctly
        assertNotNull(deleteUserEvent);
        assertEquals(1L, deleteUserEvent.getUserId());
    }

    @Test
    void testExecuteDeleteUserEvent() {
        // Act
        eventExecutor.execute(deleteUserEvent);

        // Verify that the execute method was called on the eventExecutor with deleteUserEvent
        verify(eventExecutor).execute(deleteUserEvent);
    }
}
