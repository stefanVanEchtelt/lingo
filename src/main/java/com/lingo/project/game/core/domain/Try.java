package com.lingo.project.game.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lingo.project.word.core.domain.WordDifferenceStatus;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Try implements Serializable {

    @Id
    @SequenceGenerator(name = "try_id_generator", sequenceName = "try_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "try_id_generator")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "round_id", nullable = false)
    @JsonIgnoreProperties("tries")
    private Round round;

    @NotNull
    private String value;

    public boolean isCorrect() {
        if (this.round.getWord() != null && this.round.getWord().getValue() != null) {
            return this.round.getWord().getValue().equals(this.value);
        }

        return false;
    }
}
