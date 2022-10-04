package com.db.connectville.repository;

import com.db.connectville.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    News getNewsByTitle(String title);
    News getNewsById(Integer id);
    void deleteById(Integer id);
}
