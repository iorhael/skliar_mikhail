package com.senla.repository;

import com.senla.model.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    @EntityGraph("post-simple-view")
    List<Post> findWithSimpleViewBy();

    @EntityGraph("post-simple-view")
    Optional<Post> findWithSimpleViewById(UUID id);

    @EntityGraph("post-with-tags")
    Optional<Post> findWithTagsById(UUID id);
}
