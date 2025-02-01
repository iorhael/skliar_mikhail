package com.senla.dto.publicationStatus;

import com.senla.model.PostStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PublicationStatusCreateDto {

    @NotNull
    private UUID postId;

    @NotNull
    private PostStatus statusName;

    @FutureOrPresent
    private Instant scheduledDate;
}
