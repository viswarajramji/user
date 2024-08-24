package com.demo.user.service;
import com.demo.user.Command;
import com.demo.user.CommandExecutor;
import com.demo.user.CommandExecutorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final CommandExecutorFactory commandExecutorFactory;

    @Autowired
    public UserService(CommandExecutorFactory commandExecutorFactory) {
        this.commandExecutorFactory = commandExecutorFactory;
    }

    public <T extends Command, R> R executeCommand(T command) {
        CommandExecutor<T, R> executor = commandExecutorFactory.getExecutor((Class<T>) command.getClass());
        if (executor == null) {
            throw new IllegalArgumentException("No executor found for command: " + command.getClass().getName());
        }
        return executor.execute(command);
    }
}