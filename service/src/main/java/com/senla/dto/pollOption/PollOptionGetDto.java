package com.senla.dto.pollOption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class PollOptionGetDto {

    private UUID id;

    private String description;

    private int votesCount;
}
