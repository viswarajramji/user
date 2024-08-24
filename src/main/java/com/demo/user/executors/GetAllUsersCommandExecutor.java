package com.demo.user.executors;


import com.demo.user.CommandExecutor;
import com.demo.user.command.CreateUserCommand;
import com.demo.user.command.DeleteUserCommand;
import com.demo.user.command.GetAllUsersCommand;
import com.demo.user.model.User;
import com.demo.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GetAllUsersCommandExecutor implements CommandExecutor<GetAllUsersCommand, List<User>> {

    private final UserRepository userRepository;

    @Autowired
    public GetAllUsersCommandExecutor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> execute(GetAllUsersCommand command) {
        return userRepository.findAll();
    }
}