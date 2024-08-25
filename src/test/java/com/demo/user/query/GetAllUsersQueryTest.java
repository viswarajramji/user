package com.demo.user.query;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GetAllUsersQueryTest {

    @Test
    void testGetAllUsersQueryCreation() {
        // Act
        GetAllUsersQuery query = new GetAllUsersQuery();

        // Assert
        assertNotNull(query);  // Simply ensure the query object is created
    }
}

