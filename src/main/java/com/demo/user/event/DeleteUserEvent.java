package com.demo.user.event;

import com.demo.user.api.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteUserEvent implements Event {
    private Long userId;
}
