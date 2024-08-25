package com.demo.user.command;


import com.demo.user.api.Command;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class DeleteUserCommand implements Command {

    @NotNull
    private Long userId;
}