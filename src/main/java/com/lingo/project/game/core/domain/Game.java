package com.lingo.project.game.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private GameStatus gameStatus = GameStatus.PROGRESS;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("game")
    private List<Round> rounds = new ArrayList<Round>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Column(nullable = false)
    private int score = 0;

    private String username;

    public int nextWordSize() {
        return 5 + (this.rounds.size() % 3);
    }

    @JsonIgnoreProperties("game")
    public void addRound(Round round) {
        if (!this.rounds.contains(round)) {
            this.rounds.add(round);
        }
    }

    public void end() {
        this.gameStatus = GameStatus.FINISHED;
    }

    public Round getCurrentRound() {
        if (this.rounds != null & this.rounds.size() > 0) {
            this.rounds.sort((r1, r2) -> r1.getCreatedAt().compareTo(r2.getCreatedAt()));

            return this.rounds.get(this.rounds.size() - 1);
        }

        return null;
    }

    public void correctWord() {
        this.score = this.score + 1;
    }
}
