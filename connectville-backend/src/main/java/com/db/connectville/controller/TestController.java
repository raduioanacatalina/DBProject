package com.db.connectville.controller;

import com.db.connectville.model.News;
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
        News testNews = new News();
        testNews.setId(1);
        testNews.setPublisher("Ionut Pasat");
        testNews.setPublishDate(new Date());
        testNews.setText("my first post");
        testNews.setImage("testBytes".getBytes());
        testNews.setLikes(100);
        testNews.setComments(30);
        testNews.setTopics(topics);
        newsRepository.save(testNews);
        return "OK";
    }

}
