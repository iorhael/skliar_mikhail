package com.senla.dto.poll;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class PollGetDto {

    private UUID id;

    private String postTitle;

    private String authorName;

    private String description;
}
