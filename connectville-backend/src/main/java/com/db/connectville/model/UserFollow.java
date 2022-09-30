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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="follow_id")
    private int followId;
    @Column(name="user_id")
    private int userId;
}
