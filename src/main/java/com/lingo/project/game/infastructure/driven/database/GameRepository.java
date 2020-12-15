package com.lingo.project.game.infastructure.driven.database;

import com.lingo.project.game.core.domain.Game;
import com.lingo.project.word.core.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    @Query(value = "select * from game order by score DESC limit :amountOfGames", nativeQuery = true)
    List<Game> allHighScores(@Param("amountOfGames") int amount);
}
