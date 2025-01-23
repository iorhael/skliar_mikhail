package com.senla.dto.vote;

import com.senla.model.PollOption;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VoteUpdateDto {

    @NotNull
    private PollOption pollOption;
}
