package com.senla.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
public class VoteId implements Serializable {

    private UUID userId;

    private UUID pollOptionId;
}
