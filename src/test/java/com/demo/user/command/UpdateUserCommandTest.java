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

class UpdateUserCommandTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidUpdateUserCommand() {
        // Arrange
        UpdateUserCommand command = new UpdateUserCommand(1L, "john_doe", "john.doe@example.com");

        // Act
        Set<ConstraintViolation<UpdateUserCommand>> violations = validator.validate(command);

        // Assert
        assertTrue(violations.isEmpty());
    }

    @Test
    void testUpdateUserCommand_BlankUsername_ShouldFailValidation() {
        // Arrange
        UpdateUserCommand command = new UpdateUserCommand(1L, "", "john.doe@example.com");

        // Act
        Set<ConstraintViolation<UpdateUserCommand>> violations = validator.validate(command);

        // Assert
        assertEquals(1, violations.size());
        ConstraintViolation<UpdateUserCommand> violation = violations.iterator().next();
        assertEquals("must not be blank", violation.getMessage());
        assertEquals("username", violation.getPropertyPath().toString());
    }

    @Test
    void testUpdateUserCommand_BlankEmailId_ShouldFailValidation() {
        // Arrange
        UpdateUserCommand command = new UpdateUserCommand(1L, "john_doe", "");

        // Act
        Set<ConstraintViolation<UpdateUserCommand>> violations = validator.validate(command);

        // Assert
        assertEquals(1, violations.size()); // Blank email should trigger both NotBlank and Email constraints
    }

    @Test
    void testUpdateUserCommand_InvalidEmailId_ShouldFailValidation() {
        // Arrange
        UpdateUserCommand command = new UpdateUserCommand(1L, "john_doe", "invalid-email");

        // Act
        Set<ConstraintViolation<UpdateUserCommand>> violations = validator.validate(command);

        // Assert
        assertEquals(1, violations.size());
        ConstraintViolation<UpdateUserCommand> violation = violations.iterator().next();
        assertEquals("must be a well-formed email address", violation.getMessage());
        assertEquals("emailId", violation.getPropertyPath().toString());
    }
}

