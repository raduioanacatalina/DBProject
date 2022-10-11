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
    private String image;
    //may contain links
    @Column
    private String text;
    @Column(nullable = false)
    private boolean isPinned;
    //TO DO: add relationship to user entity
    @ManyToOne(fetch = FetchType.LAZY)
    private User publisher;
    @Column
    private Date publishDate;
    @Column(name = "topics")
    @ElementCollection
    @CollectionTable(name = "news_topics", joinColumns = @JoinColumn(name = "id"))
    private Set<String> topics;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserLike> likes;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserComment> comments;
}
