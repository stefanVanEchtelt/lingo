package com.lingo.project.game.infastructure.driven.database;

import com.lingo.project.game.core.domain.Try;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TryRepository extends JpaRepository<Try, Long> {
}
