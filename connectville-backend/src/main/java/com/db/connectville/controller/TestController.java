package com.db.connectville.controller;

import com.db.connectville.model.User;
import com.db.connectville.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/test")
@RequiredArgsConstructor
public class TestController {

    private final UserRepository userRepository;

    @GetMapping
    public void test() {
        User entity = new User();
        userRepository.save(entity);
    }

}
