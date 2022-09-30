package com.db.connectville.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "news")
@NoArgsConstructor
@Getter
@ApiModel
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;
    @Column(name = "title")
    private String title;
    @Column(name = "image")
    private String image;
    @Column(name = "link")
    private String link;
    @Column(name = "text")
    private String text;
    @Column(name = "pinned")
    private boolean isPinned;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "publish_date")
    private Timestamp publishDate;


}
