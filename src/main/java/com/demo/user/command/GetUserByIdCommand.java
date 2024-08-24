package com.demo.user.command;

import com.demo.user.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserByIdCommand implements Command {
    private Long userId;
}