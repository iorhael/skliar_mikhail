package com.senla.dto.publicationStatus;

import com.senla.model.PostStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class PublicationStatusGetDto {

    private UUID id;

    private PostStatus statusName;

    private Instant scheduledDate;

    private String postTitle;
}
