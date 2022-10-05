package com.db.connectville.service;

import com.db.connectville.model.News;
import com.db.connectville.model.UserComment;
import com.db.connectville.model.UserLike;
import com.db.connectville.repository.CommentRepository;
import com.db.connectville.repository.GroupNewsRepository;
import com.db.connectville.repository.LikeRepository;
import com.db.connectville.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final GroupNewsRepository groupNewsRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final UserService userService;

    //support for editing news
    public void editNews(News news) {
        News editedNews = newsRepository.getNewsById(news.getId());
        editedNews.setPinned(news.isPinned());
        editedNews.setImage(news.getImage());
        editedNews.setText(news.getText());
        editedNews.setTitle(news.getTitle());
        editedNews.setLink(news.getLink());


    }

    //support for deleting news
    public void deleteNews(Integer newsId) {
        groupNewsRepository.deleteByNewsId(newsId);
        List<UserComment> userComments = commentRepository.getAllByNewsId(newsId);
        List<UserLike> userLikes = likeRepository.getAllByNewsId(newsId);
        for(UserComment userComment : userComments) {
            commentRepository.deleteByNewsId(userComment.getId());
        }

        for(UserLike userLike : userLikes) {
           likeRepository.deleteByNewsId(userLike.getId());
        }

        newsRepository.deleteById(newsId);

    }

}
