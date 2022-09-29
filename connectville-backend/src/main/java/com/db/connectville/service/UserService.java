package com.db.connectville.service;

import com.db.connectville.exception.UserNotFoundException;
import com.db.connectville.model.User;
import com.db.connectville.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getByUsername(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
