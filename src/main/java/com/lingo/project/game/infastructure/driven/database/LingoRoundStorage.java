package com.lingo.project.game.infastructure.driven.database;

import com.lingo.project.game.core.domain.Round;
import com.lingo.project.game.core.ports.RoundStorage;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LingoRoundStorage implements RoundStorage {
    private final RoundRepository roundRepository;

    public LingoRoundStorage(RoundRepository roundRepository) {
        this.roundRepository = roundRepository;
    }

    @Override
    public Optional<Round> find(Long id) {
        return this.roundRepository.findById(id);
    }
}
