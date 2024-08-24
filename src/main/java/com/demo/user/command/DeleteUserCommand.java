package com.demo.user.command;


import com.demo.user.api.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteUserCommand implements Command {
    private Long userId;
}