package com.db.connectville.repository;

import com.db.connectville.model.User;
import com.db.connectville.model.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<UserLike, Integer> {
    List<UserLike> getAllByNewsId(Integer newsId);
    List<UserLike> getAllByUserId(Integer userId);
    UserLike findByNewsIdAndUserId(Integer userId, Integer NewsId);
    void deleteByLikeId(Integer likeId);
    List<UserLike> deletebyNewsId(Integer newsId);

}
