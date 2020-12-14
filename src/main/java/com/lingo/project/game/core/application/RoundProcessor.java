package com.lingo.project.game.core.application;

import com.lingo.project.game.core.domain.Round;
import com.lingo.project.game.core.ports.RoundStorage;
import com.lingo.project.word.core.domain.Word;
import com.lingo.project.word.core.domain.WordFeedback;
import com.lingo.project.word.core.domain.WordFilter;
import com.lingo.project.word.infastructure.driver.service.WordService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoundProcessor {
    private final RoundStorage roundStorage;
    private final WordService wordService;

    public RoundProcessor(RoundStorage roundStorage, WordService wordService) {
        this.roundStorage = roundStorage;
        this.wordService = wordService;
    }

    public Optional<Round> find(Long id) {
        return this.roundStorage.find(id);
    }

    public WordFeedback validate(Round round, String word) {
        return this.wordService.checkIfWordsAreTheSame(round.getWord(), word);
    }
}
