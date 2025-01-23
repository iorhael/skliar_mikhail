package com.senla.dto.poll;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PollGetDto {

    private UUID id;

    private String postTitle;

    private String authorName;

    private String description;
}
