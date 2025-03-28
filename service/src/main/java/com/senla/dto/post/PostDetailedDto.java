package com.senla.dto.post;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PostDetailedDto {

    private UUID id;

    private String authorName;

    private String title;

    private String content;

    private Instant publicationDate;

    private List<String> categories;

    private List<String> tags;
}
