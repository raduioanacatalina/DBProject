package com.db.connectville.repository;

import com.db.connectville.model.UserComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository  extends JpaRepository<UserComment, Integer> {
    List<UserComment> getAllByNewsId(Integer newsId);

    List<UserComment> deletebyNewsId(Integer newsId);

}