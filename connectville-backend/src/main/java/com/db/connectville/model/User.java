package com.db.connectville.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ApiModel
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Enumerated
    @Column(columnDefinition = "smallint")
    private Role role;

    public User(int id, String lastName, String firstName, String username, String password, String email, Role role) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
