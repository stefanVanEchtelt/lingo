package com.lingo.project.game.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lingo.project.word.core.domain.Word;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
public class Round implements Serializable {

    @Id
    @SequenceGenerator(name = "round_id_generator", sequenceName = "round_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "round_id_generator")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "word_id", referencedColumnName = "id")
//    private Word word;

    private int tries;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    public Round() {
        // TODO Set word based on amount of rounds
    }
}
