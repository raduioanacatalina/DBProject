package com.db.connectville.dtos;

import com.db.connectville.model.UserComment;
import com.db.connectville.model.UserLike;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseNewsDTO {
    private int id;
    private String publisher;
    public Date publishDate;
    private String text;
    private String image;
    private boolean pinned;
    private List<UserLike> likes;
    private Set<UserComment> comments;
    private Set<String> topics;
    private String cop;
}
