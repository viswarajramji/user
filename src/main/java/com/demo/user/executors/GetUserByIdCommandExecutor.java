package com.demo.user.executors;


import com.demo.user.api.QueryExecutor;
import com.demo.user.query.GetUserByIdQuery;
import com.demo.user.model.User;
import com.demo.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class GetUserByIdCommandExecutor implements QueryExecutor<GetUserByIdQuery, User> {

    private final UserRepository userRepository;

    @Autowired
    public GetUserByIdCommandExecutor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(GetUserByIdQuery command) {
        return userRepository.findById(command.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
