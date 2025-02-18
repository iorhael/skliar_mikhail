package com.senla.dto.comment;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class CommentGetDto {

    private UUID id;

    private String authorName;

    private String content;

    private Instant createdDate;
}
