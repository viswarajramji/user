package com.demo.user.command;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateUserCommandTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidCreateUserCommand() {
        // Arrange
        CreateUserCommand command = new CreateUserCommand("john_doe", "john.doe@example.com");

        // Act
        Set<ConstraintViolation<CreateUserCommand>> violations = validator.validate(command);

        // Assert
        assertTrue(violations.isEmpty());
    }

    @Test
    void testCreateUserCommand_NullUsername_ShouldFailValidation() {
        // Arrange
        CreateUserCommand command = new CreateUserCommand(null, "john.doe@example.com");

        // Act
        Set<ConstraintViolation<CreateUserCommand>> violations = validator.validate(command);

        // Assert
        assertEquals(2, violations.size());
    }

    @Test
    void testCreateUserCommand_BlankUsername_ShouldFailValidation() {
        // Arrange
        CreateUserCommand command = new CreateUserCommand("", "john.doe@example.com");

        // Act
        Set<ConstraintViolation<CreateUserCommand>> violations = validator.validate(command);

        // Assert
        assertEquals(1, violations.size());
        ConstraintViolation<CreateUserCommand> violation = violations.iterator().next();
        assertEquals("must not be blank", violation.getMessage());
        assertEquals("username", violation.getPropertyPath().toString());
    }


    @Test
    void testCreateUserCommand_InvalidEmail_ShouldFailValidation() {
        // Arrange
        CreateUserCommand command = new CreateUserCommand("john_doe", "invalid-email");

        // Act
        Set<ConstraintViolation<CreateUserCommand>> violations = validator.validate(command);

        // Assert
        assertEquals(1, violations.size());
        ConstraintViolation<CreateUserCommand> violation = violations.iterator().next();
        assertEquals("must be a well-formed email address", violation.getMessage());
        assertEquals("emailId", violation.getPropertyPath().toString());
    }
}
