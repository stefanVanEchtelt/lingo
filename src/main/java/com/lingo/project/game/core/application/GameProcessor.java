package com.lingo.project.game.core.application;

import com.lingo.project.game.core.domain.Game;
import com.lingo.project.game.core.ports.GameStorage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameProcessor {
    private final GameStorage gameStorage;

    public GameProcessor(GameStorage gameStorage) {
        this.gameStorage = gameStorage;
    }

    public Game start(String userName) {
        return this.gameStorage.create(userName);
    }

    public Optional<Game> find(Long id) {
        return this.gameStorage.find(id);
    }

    public List<Game> top10() {
        return this.gameStorage.highScores(10);
    }
}
