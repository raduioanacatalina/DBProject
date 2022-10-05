package com.db.connectville.controller.config;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "news")
public class NewsController {
    @PostMapping("/edit/{news_id}")
    public void createAndPostNews(@PathVariable(name = "news_id") int pathId) {

    }
}
