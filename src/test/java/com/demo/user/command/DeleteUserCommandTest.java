package com.demo.user.command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeleteUserCommandTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidDeleteUserCommand() {
        // Arrange
        DeleteUserCommand command = new DeleteUserCommand(1L);

        // Act
        Set<ConstraintViolation<DeleteUserCommand>> violations = validator.validate(command);

        // Assert
        assertTrue(violations.isEmpty());
    }

    @Test
    void testDeleteUserCommand_NullUserId_ShouldFailValidation() {
        // Arrange
        DeleteUserCommand command = new DeleteUserCommand(null);

        // Act
        Set<ConstraintViolation<DeleteUserCommand>> violations = validator.validate(command);

        // Assert
        assertEquals(1, violations.size());
        ConstraintViolation<DeleteUserCommand> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("userId", violation.getPropertyPath().toString());
    }
}
