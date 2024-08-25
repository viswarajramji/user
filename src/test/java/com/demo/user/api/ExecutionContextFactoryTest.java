//package com.demo.user.api;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//class ExecutionContextFactoryTest {
//
//    @Mock
//    private ExecutionContextFactory executionContextFactory;
//
//    @Mock
//    private CommandExecutor<TestCommand, String> testCommandExecutor;
//
//    @Mock
//    private QueryExecutor<TestQuery, Integer> testQueryExecutor;
//
//    @Mock
//    private EventExecutor<TestEvent> testEventExecutor;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        // Mocking the getCommandExecutor method to return the testCommandExecutor
//        when((CommandExecutor<TestCommand, String>) executionContextFactory.getCommandExecutor(TestCommand.class))
//                .thenReturn(testCommandExecutor);
//
//        // Mocking the getQueryExecutor method to return the testQueryExecutor
//        when((QueryExecutor<TestQuery, Integer>) executionContextFactory.getQueryExecutor(TestQuery.class))
//                .thenReturn(testQueryExecutor);
//
//        // Mocking the getEventExecutor method to return the testEventExecutor
//        when((EventExecutor<TestEvent>) executionContextFactory.getEventExecutor(TestEvent.class))
//                .thenReturn(testEventExecutor);
//    }
//
//    @Test
//    void testGetCommandExecutor() {
//        // Act
//        CommandExecutor<TestCommand, String> executor = executionContextFactory.getCommandExecutor(TestCommand.class);
//
//        // Assert
//        assertNotNull(executor);
//    }
//
//    @Test
//    void testGetQueryExecutor() {
//        // Act
//        QueryExecutor<TestQuery, Integer> executor = executionContextFactory.getQueryExecutor(TestQuery.class);
//
//        // Assert
//        assertNotNull(executor);
//    }
//
//    @Test
//    void testGetEventExecutor() {
//        // Act
//        EventExecutor<TestEvent> executor = executionContextFactory.getEventExecutor(TestEvent.class);
//
//        // Assert
//        assertNotNull(executor);
//    }
//
//    // Mock classes for testing purposes
//    static class TestCommand implements Command {}
//    static class TestQuery implements Query {}
//    static class TestEvent implements Event {}
//}
