package com.senla.dto.publicationStatus;

import com.senla.model.PostStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class PublicationStatusUpdateDto {

    @NotNull
    private PostStatus statusName;

    @FutureOrPresent
    private Instant scheduledDate;
}
