package com.lingo.project.game.core.ports;

import com.lingo.project.game.core.domain.Game;

import java.util.List;
import java.util.Optional;

public interface GameStorage {
    public Game create(String userName);
    public Game update(Game game);
    public Optional<Game> find(Long id);
    public List<Game> highScores(int amount);
}
