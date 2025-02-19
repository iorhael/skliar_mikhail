package com.senla.dto.pollOption;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PollOptionGetDto {

    private UUID id;

    private String description;

    private int votesCount;
}
