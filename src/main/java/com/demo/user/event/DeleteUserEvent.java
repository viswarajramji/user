package com.demo.user.event;

import com.demo.user.api.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteUserEvent implements Event {
    private Long userId;
}
