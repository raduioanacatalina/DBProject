package com.db.connectville.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@ApiModel
@Table(name="user_like")
@Data
public class UserLike {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "like_id")
    private Integer likeId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "news_id")
    private int newsId;
}
