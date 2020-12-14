package com.lingo.project.game.core.ports;

import com.lingo.project.game.core.domain.Game;

import java.util.Optional;

public interface GameStorage {
    public Game create();
    public Game update(Game game);
    public Optional<Game> find(Long id);
}
