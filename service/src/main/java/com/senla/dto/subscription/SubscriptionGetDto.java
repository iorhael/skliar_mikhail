package com.senla.dto.subscription;

import java.time.LocalDateTime;
import java.util.UUID;

public class SubscriptionGetDto {
    private UUID id;
    private LocalDateTime startedDate;
    private LocalDateTime expiresDate;

    public SubscriptionGetDto() {
    }

    public SubscriptionGetDto(UUID id, LocalDateTime startedDate, LocalDateTime expiresDate) {
        this.id = id;
        this.startedDate = startedDate;
        this.expiresDate = expiresDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(LocalDateTime startedDate) {
        this.startedDate = startedDate;
    }

    public LocalDateTime getExpiresDate() {
        return expiresDate;
    }

    public void setExpiresDate(LocalDateTime expiresDate) {
        this.expiresDate = expiresDate;
    }
}
