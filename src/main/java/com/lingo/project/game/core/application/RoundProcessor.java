package com.lingo.project.game.core.application;

import com.lingo.project.game.core.domain.Game;
import com.lingo.project.game.core.domain.Round;
import com.lingo.project.game.core.domain.Try;
import com.lingo.project.game.core.ports.GameStorage;
import com.lingo.project.game.core.ports.RoundStorage;
import com.lingo.project.game.core.ports.TryStorage;
import com.lingo.project.game.core.ports.resource.TryResource;
import com.lingo.project.word.core.domain.Word;
import com.lingo.project.word.core.domain.WordFeedback;
import com.lingo.project.word.infastructure.driver.service.WordService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoundProcessor {
    private final RoundStorage roundStorage;
    private final GameStorage gameStorage;
    private final WordService wordService;
    private final TryStorage tryStorage;

    public RoundProcessor(RoundStorage roundStorage, GameStorage gameStorage, WordService wordService, TryStorage tryStorage) {
        this.roundStorage = roundStorage;
        this.gameStorage = gameStorage;
        this.wordService = wordService;
        this.tryStorage = tryStorage;
    }

    public Optional<Round> find(Long id) {
        return this.roundStorage.find(id);
    }

    public Game endGame(Game game) {
        game.end();
        return this.gameStorage.update(game);
    }

    public Game createNewRound(Game game) {
        Word word = this.wordService.findRandomWordByLength(game.nextWordSize());

        Round round = new Round();
        round.setGame(game);
        round.setWord(word);
        round = this.roundStorage.save(round);

        game.addRound(round);
        game.correctWord();
        game = this.gameStorage.update(game);

        return game;
    }

    public TryResource validateWord(Round round, String word) {
        WordFeedback wordFeedback = this.wordService.checkIfWordsAreTheSame(round.getWord(), word);
        Game game = round.getGame();

        Try t = Try.builder().round(round).value(word).build();
        this.tryStorage.create(t);

        if (wordFeedback.isCorrect()) {
            game = this.createNewRound(game);
        } else if (round.getTries().size() >= 5) {
            game = this.endGame(game);
        }

        return new TryResource(game, wordFeedback);
    }
}
