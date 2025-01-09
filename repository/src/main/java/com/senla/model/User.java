package com.senla.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private Subscription subscription;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    @ToString.Exclude
    private List<Post> posts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    @ToString.Exclude
    private List<Poll> polls = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    @ToString.Exclude
    private List<Vote> votes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    @ToString.Exclude
    private List<Role> roles = new ArrayList<>();

    public void setSubscription(Subscription subscription) {
        subscription.setUser(this);
        this.subscription = subscription;
    }

    public void addPost(Post post) {
        post.setUser(this);
        posts.add(post);
    }

    public void removePost(Post post) {
        posts.remove(post);
    }

    public void addPoll(Poll poll) {
        poll.setUser(this);
        polls.add(poll);
    }

    public void removePoll(Poll poll) {
        polls.remove(poll);
    }

    public void addComment(Comment comment) {
        comment.setUser(this);
        comments.add(comment);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    public void addVote(Vote vote) {
        vote.setUser(this);
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
