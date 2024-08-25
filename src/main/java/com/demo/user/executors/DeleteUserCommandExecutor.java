package com.demo.user.executors;


import com.demo.user.api.CommandExecutor;
import com.demo.user.api.Event;
import com.demo.user.command.CreateUserCommand;
import com.demo.user.command.DeleteUserCommand;
import com.demo.user.event.DeleteUserEvent;
import com.demo.user.kafka.KafkaProducer;
import com.demo.user.model.User;
import com.demo.user.repo.UserRepository;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DeleteUserCommandExecutor implements CommandExecutor<DeleteUserCommand, Void> {

    private final UserRepository userRepository;
    private final KafkaProducer kafkaProducer;

    @Autowired
    public DeleteUserCommandExecutor(UserRepository userRepository,KafkaProducer kafkaProducer) {
        this.userRepository = userRepository;
        this.kafkaProducer=kafkaProducer;
    }

    @Override
    public Void execute(DeleteUserCommand command) {
        userRepository.deleteById(command.getUserId());
        kafkaProducer.sendEvent(createEvent(command.getUserId()));
        return null;
    }

    public Event createEvent(Long userId) {
        return new DeleteUserEvent(userId);
    }

}
