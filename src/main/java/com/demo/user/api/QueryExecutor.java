package com.demo.user.api;

public interface QueryExecutor<T extends Query, R> {
    R execute(T query);
}

