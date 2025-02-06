package com.senla.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "created_date")
    @CreationTimestamp
    private Instant createdDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author", orphanRemoval = true)
    @ToString.Exclude
    private List<Post> posts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author", orphanRemoval = true)
    @ToString.Exclude
    private List<Poll> polls = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", orphanRemoval = true)
    @ToString.Exclude
    private List<Vote> votes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    @ToString.Exclude
    private List<Role> roles = new ArrayList<>();

    public void addPost(Post post) {
        post.setAuthor(this);
        posts.add(post);
    }

    public void removePost(Post post) {
        posts.remove(post);
    }

    public void addPoll(Poll poll) {
        poll.setAuthor(this);
        polls.add(poll);
    }

    public void removePoll(Poll poll) {
        polls.remove(poll);
    }

    public void addComment(Comment comment) {
        comment.setAuthor(this);
        comments.add(comment);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    public void addVote(Vote vote) {
        vote.setOwner(this);
        votes.add(vote);
    }

    public void removeVote(Vote vote) {
        votes.remove(vote);
    }

    public void addRole(Role role) {
        role.setUser(this);
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }
}
