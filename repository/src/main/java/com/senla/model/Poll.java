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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "polls")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "poll", orphanRemoval = true)
    @ToString.Exclude
    private List<PollOption> pollOptions = new ArrayList<>();

    public void addPollOption(PollOption pollOption) {
        pollOption.setPoll(this);
        pollOptions.add(pollOption);
    }

    public void removePollOption(PollOption pollOption) {
        pollOptions.remove(pollOption);
    }
}
