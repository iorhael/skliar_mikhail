package com.senla.dto.post;

import com.senla.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostGetDto {

    private UUID id;

    private User authorName;

    private String title;

    private String content;

    private LocalDateTime publicationDate;
}

