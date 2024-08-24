package com.demo.user.api;

public interface EventExecutor<T extends Event> {
    void execute(T event);
}
