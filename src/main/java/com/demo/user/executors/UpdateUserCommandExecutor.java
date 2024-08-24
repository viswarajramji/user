package com.demo.user.executors;

import com.demo.user.api.CommandExecutor;
import com.demo.user.command.UpdateUserCommand;
import com.demo.user.model.User;
import com.demo.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserCommandExecutor implements CommandExecutor<UpdateUserCommand, User> {

    private final UserRepository userRepository;

    @Autowired
    public UpdateUserCommandExecutor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(UpdateUserCommand command) {
        User user = userRepository.findById(command.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(command.getUsername());
        user.setEmailId(command.getEmailId());
        return userRepository.save(user);
    }
}