package com.demo.user.executors;


import com.demo.user.api.QueryExecutor;
import com.demo.user.query.GetAllUsersQuery;
import com.demo.user.model.User;
import com.demo.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GetAllUsersCommandExecutor implements QueryExecutor<GetAllUsersQuery, List<User>> {

    private final UserRepository userRepository;

    @Autowired
    public GetAllUsersCommandExecutor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> execute(GetAllUsersQuery command) {
        return userRepository.findAll();
    }
}