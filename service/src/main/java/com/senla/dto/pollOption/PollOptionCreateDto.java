package com.senla.dto.pollOption;

import com.senla.model.Poll;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PollOptionCreateDto {

    @NotNull
    private Poll poll;

    @NotBlank
    private String description;
}
