package com.db.connectville.controller;

import com.db.connectville.dtos.CreateAndEditNewsDTO;
import com.db.connectville.dtos.ResponseNewsDTO;
import com.db.connectville.exception.NewsNotFoundException;
import com.db.connectville.model.News;
import com.db.connectville.model.UserComment;
import com.db.connectville.repository.NewsRepository;
import com.db.connectville.repository.UserRepository;
import com.db.connectville.service.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;
    private final JWTUtils jwtUtils;

    @GetMapping("")
    public List<ResponseNewsDTO> getAllNews() {
        List<ResponseNewsDTO> rezNews = newsRepository.findAll().stream()
                .map(news -> new ResponseNewsDTO(news.getId(), news.getPublisher().getLastName() + " " +
                        news.getPublisher().getFirstName(), news.getPublishDate(), news.getText(), news.getImage(),
                        news.isPinned(), news.getLikes(), news.getComments(), news.getTopics())).collect(Collectors.toList());

        if (rezNews.isEmpty()) {
            throw new NewsNotFoundException();
        }

        return rezNews;
    }

    @GetMapping("/{id}")
    public ResponseNewsDTO getNewsById(@PathVariable(name = "id") int id) {
        List<ResponseNewsDTO> rezNews = newsRepository.findAll().stream()
                .filter(news -> news.getId() == id)
                .map(news -> new ResponseNewsDTO(news.getId(), news.getPublisher().getLastName() + " " +
                        news.getPublisher().getFirstName(), news.getPublishDate(), news.getText(), news.getImage(),
                        news.isPinned(), news.getLikes(), news.getComments(), news.getTopics())).collect(Collectors.toList());
        if (rezNews.isEmpty()) {
            throw new NewsNotFoundException();
        }

        return rezNews.get(0);
    }

    @GetMapping("/filter")
    public List<ResponseNewsDTO> getAllNewsByTopic(@RequestParam(name = "topic") List<String> topic) {
        List<ResponseNewsDTO> rezNews = newsRepository.findAll().stream()
                .filter(news -> news.getTopics().containsAll(topic))
                .map(news -> new ResponseNewsDTO(news.getId(), news.getPublisher().getLastName() + " " +
                        news.getPublisher().getFirstName(), news.getPublishDate(), news.getText(), news.getImage(),
                        news.isPinned(), news.getLikes(), news.getComments(), news.getTopics())).collect(Collectors.toList());

        if (rezNews.isEmpty()) {
            throw new NewsNotFoundException();
        }

        return rezNews;
    }

    @DeleteMapping("/{id}")
    public String deleteNewsById(@PathVariable(name = "id") int id) {
        List<ResponseNewsDTO> rezNews = newsRepository.findAll().stream()
                .filter(news -> news.getId() == id)
                .map(news -> new ResponseNewsDTO(news.getId(), news.getPublisher().getLastName() + " " +
                        news.getPublisher().getFirstName(), news.getPublishDate(), news.getText(), news.getImage(),
                        news.isPinned(), news.getLikes(), news.getComments(), news.getTopics())).collect(Collectors.toList());
        if (rezNews.isEmpty()) {
            throw new NewsNotFoundException();
        }

        newsRepository.deleteById(id);
//        return rezNews.get(0);
        return "News with id " + id + " was deleted successfully!";
    }

    @PutMapping("/{id}/pin")
    public ResponseNewsDTO pinNewsById(@PathVariable(name = "id") int id) {
        News rezNews = newsRepository.getNewsById(id);

        if (rezNews == null) {
            throw new NewsNotFoundException();
        }

        rezNews.setPinned(true);
        newsRepository.save(rezNews);

        return new ResponseNewsDTO(rezNews.getId(), rezNews.getPublisher().getLastName() + " " +
                rezNews.getPublisher().getFirstName(), rezNews.getPublishDate(), rezNews.getText(), rezNews.getImage(),
                rezNews.isPinned(), rezNews.getLikes(), rezNews.getComments(), rezNews.getTopics());
    }

    @PostMapping("/new")
    public ResponseNewsDTO createNews(@RequestBody CreateAndEditNewsDTO newNews) {
        if (newNews == null) {
            throw new NewsNotFoundException();
        }

        News createdNews = new News();
        createdNews.setText(newNews.getText());
        createdNews.setImage(newNews.getImage());
        createdNews.setPublishDate(new Date());
        createdNews.setTopics(newNews.getTopics());

        //TO DO: get logged in user and complete the createdNews fields

        newsRepository.save(createdNews);

        return new ResponseNewsDTO(createdNews.getId(), "Mocked Name", createdNews.getPublishDate(), createdNews.getText(), createdNews.getImage(),
                createdNews.isPinned(), createdNews.getLikes(), createdNews.getComments(), createdNews.getTopics());
    }

    @PutMapping("/{id}")
    public ResponseNewsDTO createNews(@PathVariable(name = "id") int id, @RequestBody CreateAndEditNewsDTO newNews) {
        News rezNews = newsRepository.getNewsById(id);

        if (rezNews == null) {
            throw new NewsNotFoundException();
        }

        if (!newNews.getText().equals("")) {
            rezNews.setText(newNews.getText());
        }

        if (!newNews.getImage().equals("")) {
            rezNews.setImage(newNews.getImage());
        }
        if(!newNews.getTopics().isEmpty()) {
            rezNews.setTopics(newNews.getTopics());
        }
        rezNews.setPublishDate(new Date());

        //TO DO: get logged in user and complete the rezNews fields

        newsRepository.save(rezNews);

        return new ResponseNewsDTO(rezNews.getId(), "Mocked Name", rezNews.getPublishDate(), rezNews.getText(), rezNews.getImage(),
                rezNews.isPinned(), rezNews.getLikes(), rezNews.getComments(), rezNews.getTopics());
    }

    @GetMapping("/{id}/comments")
    public Set<UserComment> getNewsCommentsById(@PathVariable(name = "id") int id) {
        News rezNews = newsRepository.getNewsById(id);

        if (rezNews == null) {
            throw new NewsNotFoundException();
        }

        return rezNews.getComments();
    }

}
