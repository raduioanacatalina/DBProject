package com.db.connectville.service;

import com.db.connectville.model.User;
import com.db.connectville.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

//    public User insertUser(User user) {
//        return userRepository.save(user);
//    }
}
