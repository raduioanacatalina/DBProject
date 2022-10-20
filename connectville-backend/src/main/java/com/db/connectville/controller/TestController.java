package com.db.connectville.controller;

import com.db.connectville.model.*;
import com.db.connectville.repository.NewsRepository;
import com.db.connectville.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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

        News testNews = new News();
        testNews.setId(1);
        testNews.setPublisher(user);
        testNews.setPublishDate(new Date());
        testNews.setText("my first post");
        testNews.setImage("https://i.picsum.photos/id/1002/4312/2868.jpg?hmac=5LlLE-NY9oMnmIQp7ms6IfdvSUQOzP_O3DPMWmyNxwo");

        List<UserLike> userLikes = new ArrayList<>();
        userLikes.add(new UserLike(1, 1, testNews, false));
        Set<UserComment> userComments = new HashSet<>();
        userComments.add(new UserComment(1, 1, testNews, "nice view", new Date()));

        testNews.setLikes(userLikes);
        testNews.setComments(userComments);
        testNews.setTopics(topics);
        testNews.setCop("Tech");
        newsRepository.save(testNews);
        return "OK";
    }

}
