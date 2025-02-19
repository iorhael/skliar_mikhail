package com.senla.dto;

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
public class TopCommentDto {

    private UUID id;

    private String authorName;

    private String content;

    private Instant createdDate;

    private long repliesCount;
}
