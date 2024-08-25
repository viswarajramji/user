package com.demo.user.executors;

import com.demo.user.model.User;
import com.demo.user.query.GetAllUsersQuery;
import com.demo.user.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class GetAllUsersCommandExecutorTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetAllUsersCommandExecutor getAllUsersCommandExecutor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() {
        // Arrange
        User user1 = new User(1L, "john_doe", "john.doe@example.com");
        User user2 = new User(2L, "jane_doe", "jane.doe@example.com");
        List<User> userList = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(userList);

        // Act
        List<User> result = getAllUsersCommandExecutor.execute(new GetAllUsersQuery());

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("john_doe", result.get(0).getUsername());
        assertEquals("jane_doe", result.get(1).getUsername());
    }
}

