package com.demo.user.executors;

import com.demo.user.api.CommandExecutor;
import com.demo.user.command.CreateUserCommand;
import com.demo.user.model.User;
import com.demo.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserCommandExecutor implements CommandExecutor<CreateUserCommand, User> {

    private final UserRepository userRepository;

    @Autowired
    public CreateUserCommandExecutor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(CreateUserCommand command) {
        User user = new User(null, command.getUsername(), command.getEmailId());
        return userRepository.save(user);
    }
}