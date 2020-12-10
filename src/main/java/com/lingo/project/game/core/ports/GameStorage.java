package com.lingo.project.game.core.ports;

import com.lingo.project.game.core.domain.Game;

import java.util.Optional;

public interface GameStorage {
    public Game create();
    public Optional<Game> find(Long id);
}
