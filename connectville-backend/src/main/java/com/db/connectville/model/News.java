package com.db.connectville.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "news")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiModel
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private byte[] image;
    //may contain links
    @Column
    private String text;
    @Column
    private boolean isPinned;
    //TO DO: add relationship to user entity
    @Column
    private String publisher;
    @Column
    private Date publishDate;
    @Column(name = "topics")
    @ElementCollection
    @CollectionTable(name = "news_topics", joinColumns = @JoinColumn(name = "id"))
    private Set<String> topics;
    @Column
    private int likes;
    @Column
    private int comments;
}
