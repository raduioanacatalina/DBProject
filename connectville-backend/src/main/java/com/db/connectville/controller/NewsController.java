package com.db.connectville.controller;

import com.db.connectville.dtos.CreateAndEditNewsDTO;
import com.db.connectville.dtos.ResponseNewsDTO;
import com.db.connectville.exception.NewsNotFoundException;
import com.db.connectville.model.*;
import com.db.connectville.repository.NewsRepository;
import com.db.connectville.repository.UserRepository;
import com.db.connectville.service.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Comparator;
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
                .filter(News::isPinned)
                .map(news -> new ResponseNewsDTO(news.getId(), news.getPublisher().getLastName() + " " +
                        news.getPublisher().getFirstName(), news.getPublishDate(), news.getText(), news.getImage(),
                        news.isPinned(), news.getLikes(), news.getComments(), news.getTopics())).collect(Collectors.toList());

        List<ResponseNewsDTO> unpinnedNews = newsRepository.findAll().stream()
                .filter(news -> !news.isPinned())
                .sorted(new Comparator<News>() {
                    public int compare(News n1, News n2) {
                        return n2.getPublishDate().compareTo(n1.getPublishDate());
                    }
                })
                .map(news -> new ResponseNewsDTO(news.getId(), news.getPublisher().getLastName() + " " +
                        news.getPublisher().getFirstName(), news.getPublishDate(), news.getText(), news.getImage(),
                        news.isPinned(), news.getLikes(), news.getComments(), news.getTopics())).collect(Collectors.toList());

        rezNews.addAll(unpinnedNews);

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

    @PutMapping("/{id}/unpin")
    public ResponseNewsDTO unpinNewsById(@PathVariable(name = "id") int id) {
        News rezNews = newsRepository.getNewsById(id);

        if (rezNews == null) {
            throw new NewsNotFoundException();
        }

        rezNews.setPinned(false);
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
        User user = new User();
        user.setId(2);
        createdNews.setPublisher(user);

        //TO DO: get logged in user and complete the createdNews fields

        newsRepository.save(createdNews);

        return new ResponseNewsDTO(createdNews.getId(), "Mocked Name", createdNews.getPublishDate(), createdNews.getText(), createdNews.getImage(),
                createdNews.isPinned(), createdNews.getLikes(), createdNews.getComments(), createdNews.getTopics());
    }

    @PutMapping("/{id}")
    public ResponseNewsDTO editNews(@PathVariable(name = "id") int id, @RequestBody CreateAndEditNewsDTO newNews) {
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
        if (!newNews.getTopics().isEmpty()) {
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

    @GetMapping("/{id}/likes")
    public Set<UserLike> getNewsLikesById(@PathVariable(name = "id") int id) {
        News rezNews = newsRepository.getNewsById(id);

        if (rezNews == null) {
            throw new NewsNotFoundException();
        }

        return rezNews.getLikes();
    }

    @PutMapping("/{id}/like")
    public ResponseNewsDTO likeNewsById(@PathVariable(name = "id") int id) {
        News rezNews = newsRepository.getNewsById(id);

        if (rezNews == null) {
            throw new NewsNotFoundException();
        }

        UserLike userLike = new UserLike();
        userLike.setUserId(2);
        userLike.setNews(rezNews);
        rezNews.getLikes().add(userLike);

        newsRepository.save(rezNews);

        return new ResponseNewsDTO(rezNews.getId(), rezNews.getPublisher().getLastName() + " " +
                rezNews.getPublisher().getFirstName(), rezNews.getPublishDate(), rezNews.getText(), rezNews.getImage(),
                rezNews.isPinned(), rezNews.getLikes(), rezNews.getComments(), rezNews.getTopics());
    }

    @PutMapping("/{id}/comment")
    public ResponseNewsDTO commentOnNewsById(@PathVariable(name = "id") int id, @RequestBody String comment) {
        News rezNews = newsRepository.getNewsById(id);

        if (rezNews == null) {
            throw new NewsNotFoundException();
        }

        UserComment userComment = new UserComment();
        userComment.setUserId(2);
        userComment.setNews(rezNews);
        userComment.setText(comment);

        rezNews.getComments().add(userComment);

        newsRepository.save(rezNews);

        return new ResponseNewsDTO(rezNews.getId(), rezNews.getPublisher().getLastName() + " " +
                rezNews.getPublisher().getFirstName(), rezNews.getPublishDate(), rezNews.getText(), rezNews.getImage(),
                rezNews.isPinned(), rezNews.getLikes(), rezNews.getComments(), rezNews.getTopics());
    }

}
