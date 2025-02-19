package com.senla.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "votes")
@Getter
@Setter
@ToString
public class Vote {

    @EmbeddedId
    private VoteId id = new VoteId();

    @Column(name = "vote_date")
    @CreationTimestamp
    private Instant voteDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User owner;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pollOptionId")
    @JoinColumn(name = "poll_option_id")
    @ToString.Exclude
    private PollOption pollOption;
}
