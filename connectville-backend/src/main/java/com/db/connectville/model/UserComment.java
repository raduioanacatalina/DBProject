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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;
    @Column(nullable = false)
    private int userId;
    @Column(nullable = false)
    private int newsId;
    @Column(nullable = false)
    private String text;
}
