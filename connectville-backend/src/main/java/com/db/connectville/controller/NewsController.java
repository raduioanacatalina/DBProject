package com.db.connectville.controller;

import com.db.connectville.dtos.CreateAndEditNewsDTO;
import com.db.connectville.dtos.ResponseCommentDTO;
import com.db.connectville.dtos.ResponseNewsDTO;
import com.db.connectville.exception.NewsNotFoundException;
import com.db.connectville.model.*;
import com.db.connectville.repository.NewsRepository;
import com.db.connectville.repository.UserRepository;
import com.db.connectville.service.JWTUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;
    private final JWTUtils jwtUtils;

    private User getLoggedInUser(String jwt) {
        String[] split_string = jwt.split("\\.");
        String base64EncodedBody = split_string[1];
        String body = new String(Base64.getDecoder().decode(base64EncodedBody));
        JSONObject jsonObject = new JSONObject(body);
        String username = (String) jsonObject.get("username");
        return userRepository.findByUsername(username);
    }

    @GetMapping("")
    public List<ResponseNewsDTO> getAllNews() {
        List<ResponseNewsDTO> rezNews = newsRepository.findAll().stream()
                .filter(News::isPinned)
                .map(news -> new ResponseNewsDTO(news.getId(), news.getPublisher().getLastName() + " " +
                        news.getPublisher().getFirstName(), news.getPublishDate(), news.getText(), news.getImage(),
                        news.isPinned(), news.getLikes(), news.getComments(), news.getTopics(), news.getCop()))
                .collect(Collectors.toList());

        List<ResponseNewsDTO> unpinnedNews = newsRepository.findAll().stream()
                .filter(news -> !news.isPinned())
                .sorted((n1, n2) -> n2.getPublishDate().compareTo(n1.getPublishDate()))
                .map(news -> new ResponseNewsDTO(news.getId(), news.getPublisher().getLastName() + " " +
                        news.getPublisher().getFirstName(), news.getPublishDate(), news.getText(), news.getImage(),
                        news.isPinned(), news.getLikes(), news.getComments(), news.getTopics(), news.getCop()))
                .collect(Collectors.toList());

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
                        news.isPinned(), news.getLikes(), news.getComments(), news.getTopics(), news.getCop()))
                .collect(Collectors.toList());
        if (rezNews.isEmpty()) {
            throw new NewsNotFoundException();
        }

        return rezNews.get(0);
    }

    @GetMapping("/filter")
    public List<ResponseNewsDTO> getAllNewsByTopic(@RequestParam(name = "topics") List<String> topics) {
        List<ResponseNewsDTO> rezNews = newsRepository.findAll().stream()
                .filter(news -> news.getTopics().containsAll(topics))
                .map(news -> new ResponseNewsDTO(news.getId(), news.getPublisher().getLastName() + " " +
                        news.getPublisher().getFirstName(), news.getPublishDate(), news.getText(), news.getImage(),
                        news.isPinned(), news.getLikes(), news.getComments(), news.getTopics(), news.getCop()))
                .collect(Collectors.toList());

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
                        news.isPinned(), news.getLikes(), news.getComments(), news.getTopics(), news.getCop()))
                .collect(Collectors.toList());
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
                rezNews.isPinned(), rezNews.getLikes(), rezNews.getComments(), rezNews.getTopics(), rezNews.getCop());
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
                rezNews.isPinned(), rezNews.getLikes(), rezNews.getComments(), rezNews.getTopics(), rezNews.getCop());
    }

    @PostMapping("/new")
    public ResponseNewsDTO createNews(@RequestBody CreateAndEditNewsDTO newNews, HttpServletRequest http) {

        if (newNews == null) {
            throw new NewsNotFoundException();
        }

        News createdNews = new News();
        createdNews.setText(newNews.getText());
        createdNews.setImage(newNews.getImage());
        createdNews.setPublishDate(new Date());
        createdNews.setTopics(newNews.getTopics());
        createdNews.setCop("HR");
        User user = getLoggedInUser(http.getHeader("Authorization"));
        createdNews.setPublisher(user);

        newsRepository.save(createdNews);

        return new ResponseNewsDTO(createdNews.getId(), createdNews.getPublisher().getLastName() + " " +
                createdNews.getPublisher().getFirstName(), createdNews.getPublishDate(),
                createdNews.getText(), createdNews.getImage(), createdNews.isPinned(), createdNews.getLikes(),
                createdNews.getComments(), createdNews.getTopics(), createdNews.getCop());
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
                rezNews.isPinned(), rezNews.getLikes(), rezNews.getComments(), rezNews.getTopics(), rezNews.getCop());
    }

    @GetMapping("/{id}/comments")
    public List<ResponseCommentDTO> getNewsCommentsById(@PathVariable(name = "id") int id) {
        News rezNews = newsRepository.getNewsById(id);

        if (rezNews == null) {
            throw new NewsNotFoundException();
        }

        return rezNews.getComments().stream()
                .map(comment -> new ResponseCommentDTO(userRepository.getUserById(comment.getUserId()).getLastName() + " " +
                        userRepository.getUserById(comment.getUserId()).getFirstName(), comment.getText())).collect(Collectors.toList());

    }

    @GetMapping("/{id}/likes")
    public List<String> getNewsLikesById(@PathVariable(name = "id") int id) {
        News rezNews = newsRepository.getNewsById(id);

        if (rezNews == null) {
            throw new NewsNotFoundException();
        }

        return rezNews.getLikes().stream()
                .map(like -> userRepository.getUserById(like.getUserId()).getLastName() + " " +
                        userRepository.getUserById(like.getUserId()).getFirstName()).collect(Collectors.toList());
    }

    @PutMapping("/{id}/like")
    public ResponseNewsDTO likeNewsById(@PathVariable(name = "id") int id, HttpServletRequest http) {
        News rezNews = newsRepository.getNewsById(id);

        if (rezNews == null) {
            throw new NewsNotFoundException();
        }

        UserLike userLike = new UserLike();
        User user = getLoggedInUser(http.getHeader("Authorization"));
        userLike.setUserId(user.getId());
        userLike.setNews(rezNews);
        rezNews.getLikes().add(userLike);

        newsRepository.save(rezNews);

        return new ResponseNewsDTO(rezNews.getId(), rezNews.getPublisher().getLastName() + " " +
                rezNews.getPublisher().getFirstName(), rezNews.getPublishDate(), rezNews.getText(), rezNews.getImage(),
                rezNews.isPinned(), rezNews.getLikes(), rezNews.getComments(), rezNews.getTopics(), rezNews.getCop());
    }

    @PutMapping("/{id}/comment")
    public ResponseNewsDTO commentOnNewsById(@PathVariable(name = "id") int id, @RequestBody String comment, HttpServletRequest http) {
        News rezNews = newsRepository.getNewsById(id);

        if (rezNews == null) {
            throw new NewsNotFoundException();
        }

        UserComment userComment = new UserComment();
        User user = getLoggedInUser(http.getHeader("Authorization"));
        userComment.setUserId(user.getId());
        userComment.setNews(rezNews);
        userComment.setText(comment);

        rezNews.getComments().add(userComment);

        newsRepository.save(rezNews);

        return new ResponseNewsDTO(rezNews.getId(), rezNews.getPublisher().getLastName() + " " +
                rezNews.getPublisher().getFirstName(), rezNews.getPublishDate(), rezNews.getText(), rezNews.getImage(),
                rezNews.isPinned(), rezNews.getLikes(), rezNews.getComments(), rezNews.getTopics(), rezNews.getCop());
    }

    @GetMapping("/search")
    public List<ResponseNewsDTO> getAllNewsByKeywords(@RequestParam(name = "keywords") List<String> keywords) {
        List<ResponseNewsDTO> rezNews = newsRepository.findAll().stream()
                .filter(news -> keywords.stream().allMatch(news.getText()::contains))
                .map(news -> new ResponseNewsDTO(news.getId(), news.getPublisher().getLastName() + " " +
                        news.getPublisher().getFirstName(), news.getPublishDate(), news.getText(), news.getImage(),
                        news.isPinned(), news.getLikes(), news.getComments(), news.getTopics(), news.getCop()))
                .collect(Collectors.toList());

        if (rezNews.isEmpty()) {
            throw new NewsNotFoundException();
        }

        return rezNews;
    }

    @GetMapping("/CoP/{cop}")
    public List<ResponseNewsDTO> getAllNewsByCop(@PathVariable(name = "cop") String cop) {
        List<ResponseNewsDTO> rezNews = newsRepository.findAll().stream()
                .filter(news -> news.getCop().equals(cop))
                .map(news -> new ResponseNewsDTO(news.getId(), news.getPublisher().getLastName() + " " +
                        news.getPublisher().getFirstName(), news.getPublishDate(), news.getText(), news.getImage(),
                        news.isPinned(), news.getLikes(), news.getComments(), news.getTopics(), news.getCop()))
                .collect(Collectors.toList());

        if (rezNews.isEmpty()) {
            throw new NewsNotFoundException();
        }

        return rezNews;
    }

    @GetMapping("/{cop}/filter")
    public List<ResponseNewsDTO> getAllNewsByCopAndTopics(@PathVariable(name = "cop") String cop, @RequestParam(name = "topics") List<String> topics) {
        List<ResponseNewsDTO> rezNews = newsRepository.findAll().stream()
                .filter(news -> news.getCop().equals(cop) && news.getTopics().containsAll(topics))
                .map(news -> new ResponseNewsDTO(news.getId(), news.getPublisher().getLastName() + " " +
                        news.getPublisher().getFirstName(), news.getPublishDate(), news.getText(), news.getImage(),
                        news.isPinned(), news.getLikes(), news.getComments(), news.getTopics(), news.getCop()))
                .collect(Collectors.toList());

        if (rezNews.isEmpty()) {
            throw new NewsNotFoundException();
        }

        return rezNews;
    }

}
