package com.senla.dto.comment;

import com.senla.model.Comment;
import com.senla.model.Post;
import com.senla.model.User;
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
public class CommentCreateDto {

    @NotNull
    private Post post;

    @NotNull
    private User author;

    @NotBlank
    private String content;

    private Comment parentComment;
}
