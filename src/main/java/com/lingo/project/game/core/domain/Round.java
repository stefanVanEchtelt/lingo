package com.lingo.project.game.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lingo.project.word.core.domain.Word;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Round implements Serializable {

    @Id
    @SequenceGenerator(name = "round_id_generator", sequenceName = "round_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "round_id_generator")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    @JsonIgnoreProperties("rounds")
    private Game game;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "word_id", referencedColumnName = "id")
    private Word word;

    @OneToMany(mappedBy = "round", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("round")
    private List<Try> tries = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    public boolean hasAnswedCorrectly() {
        for (Try t: this.tries) {
            if (t.isCorrect()) {
                return true;
            }
        }
        return false;
    }

    public RoundStatus getStatus() {
        if (this.tries.size() == 0) {
            return RoundStatus.START;
        } else if (this.hasAnswedCorrectly()) {
            return RoundStatus.WON;
        } else if (this.tries.size() >= 5) {
            return RoundStatus.LOST;
        }

        return RoundStatus.PROGRESS;
    }

    public boolean reactedInTime() {
        return true;
    }

    public boolean canAnswer() {
        return this.getStatus() == RoundStatus.START || this.getStatus() == RoundStatus.PROGRESS;
    }

    public Long getId() {
        return id;
    }

    public List<Try> getTries() {
        return tries;
    }

    public void setTries(List<Try> tries) {
        this.tries = tries;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Word getWord() {
        return word;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
