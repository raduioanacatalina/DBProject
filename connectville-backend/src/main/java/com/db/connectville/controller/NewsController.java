package com.db.connectville.controller;

import com.db.connectville.model.News;
import com.db.connectville.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsRepository newsRepository;

    @GetMapping
    public List<News> getAllNews() {
        return newsRepository.getAllNews();
    }

}
