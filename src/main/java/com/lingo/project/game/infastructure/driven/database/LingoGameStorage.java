package com.lingo.project.game.infastructure.driven.database;

import com.lingo.project.game.core.domain.Game;
import com.lingo.project.game.core.domain.Round;
import com.lingo.project.game.core.ports.GameStorage;
import com.lingo.project.word.core.domain.Word;
import com.lingo.project.word.infastructure.driven.database.WordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LingoGameStorage implements GameStorage {
    private final GameRepository gameRepository;
    private final RoundRepository roundRepository;
    private final WordRepository wordRepository;

    @Override
    public Game create(String userName) {
        Game game = this.gameRepository.save(Game.builder().username(userName).build());
        Word word = this.wordRepository.findRandomByWordLength(5);

        Round round = new Round();
        round.setGame(game);
        round.setWord(word);
        this.roundRepository.save(round);

        game.addRound(round);
        return game;
    }

    public Game update(Game game) {
        return this.gameRepository.save(game);
    }

    @Override
    public Optional<Game> find(Long id) {
        return this.gameRepository.findById(id);
    }

    @Override
    public List<Game> highScores(int amount) {
        return this.gameRepository.allHighScores(amount);
    }
}
