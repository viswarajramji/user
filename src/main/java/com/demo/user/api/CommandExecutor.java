package com.demo.user.api;

public interface CommandExecutor<T extends Command, R> {
    R execute(T command);
}

