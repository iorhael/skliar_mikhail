package com.senla.dto.vote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VoteGetDto {

    private String pollOptionDescription;

    private String ownerName;

    private LocalDateTime voteDate;
}
