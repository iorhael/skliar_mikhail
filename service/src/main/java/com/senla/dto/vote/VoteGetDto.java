package com.senla.dto.vote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VoteGetDto {

    private String pollOptionDescription;

    private String ownerName;

    private Instant voteDate;
}
