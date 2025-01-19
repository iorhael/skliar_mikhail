package com.senla.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostGetDto {

    private UUID id;

    private String authorName;

    private String title;

    private String content;

    private Instant publicationDate;
}

