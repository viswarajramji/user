package com.demo.user.query;

import com.demo.user.api.Command;
import com.demo.user.api.Query;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class GetUserByIdQuery implements Query {
    @NonNull
    private Long userId;
}