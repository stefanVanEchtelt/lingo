package com.lingo.project.game.infastructure.driven.database;

import com.lingo.project.game.core.domain.Try;
import com.lingo.project.game.core.ports.TryStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LingoTryStorage implements TryStorage {
    private final TryRepository tryRepository;

    @Override
    public Try create(Try t) {
        return this.tryRepository.save(t);
    }
}
