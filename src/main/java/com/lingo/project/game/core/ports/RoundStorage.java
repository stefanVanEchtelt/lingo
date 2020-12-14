package com.lingo.project.game.core.ports;

import com.lingo.project.game.core.domain.Round;

import java.util.Optional;

public interface RoundStorage {
    public Optional<Round> find(Long id);
    public Round save(Round round);
}
