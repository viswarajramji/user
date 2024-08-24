package com.demo.user;

public interface CommandExecutor<T extends Command, R> {
    R execute(T command);
}
