package com.demo.user.query;

import com.demo.user.api.Command;
import com.demo.user.api.Query;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserByIdQuery implements Query {
    private Long userId;
}