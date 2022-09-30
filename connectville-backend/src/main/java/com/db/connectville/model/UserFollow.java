package com.db.connectville.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@ApiModel
@Table(name = "user_follow")
public class UserFollow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int followId;
    @Column(nullable = false)
    private int userId;
}
