package com.senla.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "views_total")
    private Long viewsTotal;

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "publication_date")
    private LocalDateTime publicationDate;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "post")
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private PublicationStatus publicationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_plan_id")
    @ToString.Exclude
    private SubscriptionPlan subscriptionPlan;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post", orphanRemoval = true)
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "post_tags",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    @ToString.Exclude
    private List<Tag> tags = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "post_categories",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    @ToString.Exclude
    private List<Category> categories = new ArrayList<>();

    public void setPublicationStatus(PublicationStatus publicationStatus) {
        publicationStatus.setPost(this);
        this.publicationStatus = publicationStatus;
    }

    public void addComment(Comment comment) {
        comment.setPost(this);
        comments.add(comment);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    public void addTag(Tag tag) {
        tag.getPosts().add(this);
        tags.add(tag);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    public void addCategory(Category category) {
        category.getPosts().add(this);
        categories.add(category);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
    }
}
