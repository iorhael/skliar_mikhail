package com.senla.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "publication_statuses")
@NamedEntityGraph(name = "publicationStatus-with-post",
        attributeNodes = @NamedAttributeNode("post"))
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PublicationStatus {

    @Id
    private UUID id;

    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "status_name")
    private PostStatus statusName;

    @Column(name = "scheduled_date")
    private Instant scheduledDate;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    @ToString.Exclude
    private Post post;
}
