package com.demo.user.kafka;

import com.demo.user.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaCommandMessage {
    private String commandType;
    private Command payload;
}