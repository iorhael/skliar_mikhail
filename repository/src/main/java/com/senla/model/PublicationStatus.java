package com.senla.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "publication_statuses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PublicationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "status_name")
    private PostStatus statusName;

    @Column(name = "scheduled_date")
    private LocalDateTime scheduledDate;

    @OneToOne
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    private Post post;
}
