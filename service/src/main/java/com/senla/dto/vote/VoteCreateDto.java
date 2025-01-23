package com.senla.dto.vote;

import com.senla.model.PollOption;
import com.senla.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VoteCreateDto {

    @NotNull
    private PollOption pollOption;

    @NotNull
    private User user;

    private Instant voteDate;
}
