package com.lingo.project.game.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lingo.project.word.core.domain.Word;
import com.lingo.project.word.core.ports.WordStorage;
import com.lingo.project.word.infastructure.driven.database.LingoWordStorage;
import com.lingo.project.word.infastructure.driven.database.WordRepository;
import com.lingo.project.word.infastructure.driver.service.WordService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @JsonIgnoreProperties("rounds")
    private Game game;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "word_id", referencedColumnName = "id")
    private Word word;

    @OneToMany(mappedBy = "round", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("round")
    private List<Try> tries = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    public RoundStatus getStatus() {
        if (this.tries.size() == 0) {
            return RoundStatus.START;
        } else if (this.tries.size() >= 5) {
            return RoundStatus.LOST;
        }

        return RoundStatus.PROGRESS;
    }

    public Round() {
        int wordLength = 5;
        if (this.game != null && this.game.getRounds() != null) {
            // TODO Set word based on amount of rounds
        }

        // TODO word fix

//        WordService wordService = new WordService();
//        Word word = this.wordService.findRandomWordByLength(wordLength);

        this.setWord(word);
    }
}
