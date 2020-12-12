package com.lingo.project.game.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Game implements Serializable {
    @Id
    @SequenceGenerator(name = "game_id_generator", sequenceName = "game_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_id_generator")
    private Long id;

    @Column(length = 32, columnDefinition = "varchar(32) default 'PROGRESS'")
    @Enumerated(EnumType.STRING)
    private Status status = Status.PROGRESS;

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("game")
    private List<Round> rounds = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    public void addRound(Round round) {
        if (this.rounds.contains(round)) {
            this.rounds.add(round);
        }
    }
}
