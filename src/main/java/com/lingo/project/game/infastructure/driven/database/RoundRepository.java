package com.lingo.project.game.infastructure.driven.database;

import com.lingo.project.game.core.domain.Round;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundRepository extends JpaRepository<Round, Long> {
}
