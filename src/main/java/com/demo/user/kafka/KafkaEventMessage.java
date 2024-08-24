package com.demo.user.kafka;

import com.demo.user.api.Command;
import com.demo.user.api.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaEventMessage {
    private String eventType;
    private Event payload;
}