package com.db.connectville.model;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel
@Table(name="user_like")
@Data
public class UserLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int userId;
    @Column(name = "news_id", nullable = false)
    private int newsId;
}
