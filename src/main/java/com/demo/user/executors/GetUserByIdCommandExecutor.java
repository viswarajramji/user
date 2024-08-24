package com.demo.user.executors;


import com.demo.user.CommandExecutor;
import com.demo.user.command.CreateUserCommand;
import com.demo.user.command.GetUserByIdCommand;
import com.demo.user.model.User;
import com.demo.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class GetUserByIdCommandExecutor implements CommandExecutor<GetUserByIdCommand, User> {

    private final UserRepository userRepository;

    @Autowired
    public GetUserByIdCommandExecutor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(GetUserByIdCommand command) {
        return userRepository.findById(command.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
