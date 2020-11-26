package com.lingo.project.repository;

import com.lingo.project.domain.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
