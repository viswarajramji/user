package com.demo.user.command;

import com.demo.user.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UpdateUserCommand implements Command {
    private Long userId;
    private String username;
    private String emailId;
}