package com.db.connectville.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateAndEditNewsDTO {
    private String text;
    private String image;
    private Set<String> topics;
}
