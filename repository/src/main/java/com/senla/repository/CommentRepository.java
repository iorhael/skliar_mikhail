package com.senla.repository;

import com.senla.dto.TopCommentDto;
import com.senla.model.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    @EntityGraph("comment-with-author")
    Optional<Comment> findWithAuthorById(UUID id);

    @Query(value = """
            SELECT
                c.id,
                a.username,
                c.content,
                c.created_date,
                COUNT(child_c.id)
            FROM
                comments c
            LEFT JOIN
                users a ON a.id = c.author_id
            LEFT JOIN
                comments child_c ON child_c.parent_id = c.id
            WHERE
                c.post_id = :postId
                AND c.parent_id IS NULL
            GROUP BY
                c.id, a.username;
            """, nativeQuery = true)
    List<TopCommentDto> findTopLevel(@Param("postId") UUID postId, Pageable pageable);

    @EntityGraph("comment-with-author")
    List<Comment> findRepliesByParentCommentId(UUID parentId, Pageable pageable);
}
