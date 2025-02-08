package com.senla.dto.vote;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
public class VoteGetDto {

    private Instant voteDate;
}
