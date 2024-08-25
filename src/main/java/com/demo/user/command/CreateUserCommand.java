package com.demo.user.command;
import com.demo.user.api.Command;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class CreateUserCommand implements Command {
    @NotBlank
    @NotNull
    private String username;

    @NotBlank
    @NonNull
    @Email
    private String emailId;
}
