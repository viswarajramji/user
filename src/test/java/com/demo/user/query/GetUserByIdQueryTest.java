package com.demo.user.query;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GetUserByIdQueryTest {

    @Test
    void testGetUserByIdQueryCreation() {
        // Arrange
        Long userId = 1L;

        // Act
        GetUserByIdQuery query = new GetUserByIdQuery(userId);

        // Assert
        assertNotNull(query);
        assertEquals(userId, query.getUserId());
    }

    @Test
    void testGetUserByIdQueryWithNullUserId() {
        // Arrange & Act
        try {
            new GetUserByIdQuery(null);
        } catch (NullPointerException e) {
            // Assert
            assertEquals("userId is marked non-null but is null", e.getMessage());
        }
    }
}
