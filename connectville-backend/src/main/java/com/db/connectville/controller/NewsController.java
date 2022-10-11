package com.db.connectville.controller;

import com.db.connectville.dtos.ResponseNewsDTO;
import com.db.connectville.model.News;
import com.db.connectville.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsRepository newsRepository;

    @GetMapping("")
    public List<ResponseNewsDTO> getAllNews() {
        return newsRepository.findAll().stream()
                .map(news -> new ResponseNewsDTO(news.getId(), news.getPublisher().getLastName() + news.getPublisher().getFirstName(),
                        news.getPublishDate(), news.getText(), news.getImage(), news.getLikes(), news.getComments(),
                        news.getTopics())).collect(Collectors.toList());
    }

    @GetMapping("/filter")
    public List<ResponseNewsDTO> getAllNewsByTopic(@RequestParam(name = "topic") String topic) {
        return newsRepository.findAll().stream()
                .filter(news -> news.getTopics().contains(topic))
                .map(news -> new ResponseNewsDTO(news.getId(), news.getPublisher().getLastName() + news.getPublisher(), news.getPublishDate(), news.getText(),
                        news.getImage(), news.getLikes(), news.getComments(), news.getTopics())).collect(Collectors.toList());
    }

}
