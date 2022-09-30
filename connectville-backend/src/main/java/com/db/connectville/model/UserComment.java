package com.db.connectville.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="user_comment")
@NoArgsConstructor
@Getter
@ApiModel
public class UserComment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name ="comment_id")
    private int commentId;
    @Column(name="user_id")
    private int userId;
    @Column(name="news_id")
    private int newsId;
    @Column(name="text")
    private String text;
}
