package com.db.connectville.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "news")
@NoArgsConstructor
@Getter
@Setter
@ApiModel
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "image")
    //TO DO: switch to binary data type
    private byte[] image;
    @Column(name = "link")
    private String link;
    @Column(name = "text")
    private String text;
    @Column(name = "pinned")
    private boolean isPinned;
    //TO DO: add relationship to user entity
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "publish_date")
    private Timestamp publishDate;


}
