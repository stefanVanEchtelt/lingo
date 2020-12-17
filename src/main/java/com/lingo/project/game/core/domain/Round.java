package com.lingo.project.game.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lingo.project.word.core.domain.Word;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Round implements Serializable {

    @Id
    @Getter
    @SequenceGenerator(name = "round_id_generator", sequenceName = "round_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "round_id_generator")
    private Long id;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    @JsonIgnoreProperties("rounds")
    private Game game;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "word_id", referencedColumnName = "id")
    private Word word;

    @Getter
    @Setter
    @OneToMany(mappedBy = "round", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("round")
    private List<Try> tries = new ArrayList<>();

    @Getter
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    public boolean hasAnsweredCorrectly() {
        for (Try t: this.tries) {
            if (t.isCorrect()) {
                return true;
            }
        }
        return false;
    }

    public RoundStatus getStatus() {
        if (this.tries == null || this.tries.size() == 0) {
            return RoundStatus.START;
        } else if (this.hasAnsweredCorrectly()) {
            return RoundStatus.WON;
        } else if (this.tries.size() >= 5) {
            return RoundStatus.LOST;
        }

        return RoundStatus.PROGRESS;
    }

    private Try getLastTry() {
        if (this.tries != null && this.tries.size() > 0) {
            this.tries.sort((r1, r2) -> r1.getCreatedAt().compareTo(r2.getCreatedAt()));
            return this.tries.get(this.tries.size() - 1);
        }

        return null;
    }

    public boolean reactedInTime() {
        Try lastTry = this.getLastTry();
        if (lastTry != null && lastTry.getCreatedAt() != null) {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            int secondsBetween = (int) ((now.getTime() - lastTry.getCreatedAt().getTime()) / 1000);
            if (secondsBetween > 10) {
                return false;
            }
        }

        return true;
    }

    public boolean canAnswer() {
        return this.getStatus() == RoundStatus.START || this.getStatus() == RoundStatus.PROGRESS;
    }
}
