package com.senla.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "poll_options")
@NamedEntityGraph(name = "pollOption-with-votes",
        attributeNodes = @NamedAttributeNode("votes"))
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PollOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
    @ToString.Exclude
    private Poll poll;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pollOption", orphanRemoval = true)
    @ToString.Exclude
    private List<Vote> votes = new ArrayList<>();

    public void addVote(Vote vote) {
        vote.setPollOption(this);
        votes.add(vote);
    }

    public void removeVote(Vote vote) {
        votes.remove(vote);
    }
}
