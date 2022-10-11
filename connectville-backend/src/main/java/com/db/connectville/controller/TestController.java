package com.db.connectville.controller;

import com.db.connectville.model.*;
import com.db.connectville.repository.NewsRepository;
import com.db.connectville.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final UserRepository userRepository;
    private final NewsRepository newsRepository;

    @GetMapping("")
    public String test() {
        Set<String> topics = new HashSet<>();
        topics.add("backend");
        topics.add("java");
        topics.add("AI");
        User user = new User(1, "Pasat", "Ionut", "ionut.pasat", "pass", "ipasat@gmail.com", Role.admin);
        Set<UserLike> userLikes = new HashSet<>();
        userLikes.add(new UserLike(1, 1, 1));
        Set<UserComment> userComments = new HashSet<>();
        userComments.add(new UserComment(1, 1, 1, "nice view"));
        News testNews = new News();
        testNews.setId(1);
        testNews.setPublisher(user);
        testNews.setPublishDate(new Date());
        testNews.setText("my first post");
        testNews.setImage("https://i.picsum.photos/id/1002/4312/2868.jpg?hmac=5LlLE-NY9oMnmIQp7ms6IfdvSUQOzP_O3DPMWmyNxwo");
        testNews.setLikes(userLikes);
        testNews.setComments(userComments);
        testNews.setTopics(topics);
        newsRepository.save(testNews);
        return "OK";
    }

}
