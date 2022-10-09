package com.db.connectville.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
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
    private byte[] image;
    private int likes;
    private int comments;
    private Set<String> topics;
}
