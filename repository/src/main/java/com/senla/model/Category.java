package com.senla.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categories")
@Getter
@Setter
@ToString
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @ToString.Exclude
    private Category parentCategory;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "parentCategory")
    @ToString.Exclude
    private List<Category> childrenCategories = new ArrayList<>();

    @ManyToMany(mappedBy = "categories")
    @ToString.Exclude
    private List<Post> posts = new ArrayList<>();

    public void addChildrenCategory(Category category) {
        category.setParentCategory(this);
        childrenCategories.add(category);
    }

    public void removeChildrenCategory(Category category) {
        childrenCategories.remove(category);
    }

    public void addPost(Post post) {
        post.getCategories().add(this);
        posts.add(post);
    }

    public void removePost(Post post) {
        posts.remove(post);
    }
}
