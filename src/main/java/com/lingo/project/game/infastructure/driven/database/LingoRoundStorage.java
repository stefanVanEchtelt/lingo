package com.lingo.project.game.infastructure.driven.database;

import com.lingo.project.game.core.domain.Round;
import com.lingo.project.game.core.ports.RoundStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LingoRoundStorage implements RoundStorage {
    private final RoundRepository roundRepository;

    @Override
    public Optional<Round> find(Long id) {
        return this.roundRepository.findById(id);
    }

    @Override
    public Round save(Round round) {
        return this.roundRepository.save(round);
    }
}
