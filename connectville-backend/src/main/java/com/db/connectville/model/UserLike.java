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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int likeId;
    @Column(nullable = false)
    private int userId;
    @Column(nullable = false)
    private int newsId;
}
