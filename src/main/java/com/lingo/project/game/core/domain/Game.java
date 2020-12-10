package com.lingo.project.game.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @SequenceGenerator(name = "game_id_generator", sequenceName = "game_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_id_generator")
    private Long id;

    @Column(length = 32, columnDefinition = "varchar(32) default 'PROGRESS'")
    @Enumerated(EnumType.STRING)
    private Status status = Status.PROGRESS;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Round.class, cascade = CascadeType.ALL)
    private List<Round> rounds = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;
}
