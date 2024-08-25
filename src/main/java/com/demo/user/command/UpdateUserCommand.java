package com.demo.user.command;

import com.demo.user.api.Command;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UpdateUserCommand implements Command {
    @NonNull
    private Long userId;

    @NonNull
    @NotBlank
    private String username;

    @NonNull
    @NotBlank
    @Email
    private String emailId;
}