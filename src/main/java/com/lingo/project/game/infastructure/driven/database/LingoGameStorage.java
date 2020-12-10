package com.lingo.project.game.infastructure.driven.database;

import com.lingo.project.game.core.domain.Game;
import com.lingo.project.game.core.ports.GameStorage;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LingoGameStorage implements GameStorage {
    private final GameRepository gameRepository;

    public LingoGameStorage(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game create() {
        return this.gameRepository.save(new Game());
    }

    @Override
    public Optional<Game> find(Long id) {
        return this.gameRepository.findById(id);
    }
}
