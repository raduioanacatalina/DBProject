package com.db.connectville.repository;

import com.db.connectville.model.GroupNews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupNewsRepository extends JpaRepository<GroupNews, Integer> {
    int deleteByGroupIdAndNewsId(Integer groupId, Integer newsId);
    int deleteByNewsId(Integer newsId);
    List<GroupNews> findGroupNewsByGroupId(Integer groupId);
    Optional<GroupNews> findGroupNewsByNewsId(Integer newsId);

}
