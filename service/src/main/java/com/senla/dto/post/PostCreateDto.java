package com.senla.dto.post;

import com.senla.model.SubscriptionPlan;
import com.senla.model.User;
import jakarta.validation.constraints.NotBlank;
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
public class PostCreateDto {

    @NotNull
    private User author;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Instant publicationDate;

    @NotNull
    private SubscriptionPlan subscriptionPlan;
}
