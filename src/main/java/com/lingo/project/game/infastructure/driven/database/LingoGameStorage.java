package com.lingo.project.game.infastructure.driven.database;

import com.lingo.project.game.core.domain.Game;
import com.lingo.project.game.core.domain.Round;
import com.lingo.project.game.core.ports.GameStorage;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LingoGameStorage implements GameStorage {
    private final GameRepository gameRepository;
    private final RoundRepository roundRepository;

    public LingoGameStorage(GameRepository gameRepository, RoundRepository roundRepository) {
        this.gameRepository = gameRepository;
        this.roundRepository = roundRepository;
    }

    @Override
    public Game create() {
        Game game = new Game();
        Round round = new Round();
        round.setGame(game);

        game = this.gameRepository.save(game);
        this.roundRepository.save(round);

        return game;
    }

    @Override
    public Optional<Game> find(Long id) {
        return this.gameRepository.findById(id);
    }
}
