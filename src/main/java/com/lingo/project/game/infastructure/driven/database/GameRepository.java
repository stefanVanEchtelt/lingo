package com.lingo.project.game.infastructure.driven.database;

import com.lingo.project.game.core.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
