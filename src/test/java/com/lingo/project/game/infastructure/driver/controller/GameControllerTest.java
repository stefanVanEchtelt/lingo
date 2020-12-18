package com.lingo.project.game.infastructure.driver.controller;

import com.lingo.project.game.core.application.GameProcessor;
import com.lingo.project.game.core.domain.Game;
import com.lingo.project.game.core.domain.GameStatus;
import com.lingo.project.game.core.domain.Round;
import com.lingo.project.game.core.domain.Try;
import com.lingo.project.word.core.domain.Word;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = GameController.class)
@ActiveProfiles("test")
class GameControllerTest {

    @MockBean
    GameProcessor gameProcessor;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void startShouldReturnGame() throws Exception {
        List<Round> rounds = new ArrayList<Round>();
        Word word = Word.builder().value("testen").build();
        Round round = Round.builder().id((long) 2).tries(new ArrayList<Try>()).word(word).build();
        rounds.add(round);
        Game game = Game.builder().id((long) 1).gameStatus(GameStatus.FINISHED)
                .createdAt(new Timestamp(System.currentTimeMillis())).score(1)
                .rounds(rounds).build();

        Mockito.when(gameProcessor.start("myUserName")).thenReturn(game);

        mockMvc.perform(post("/game/start/myUserName"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(game.getId()))
                .andExpect(jsonPath("$.gameStatus").value(game.getGameStatus().name()))
                .andExpect(jsonPath("$.currentRound.roundId").value(game.getCurrentRound().getId()))
                .andExpect(jsonPath("$.currentRound.worthLength").value(game.getCurrentRound().getWord().getValue().length()))
                .andExpect(jsonPath("$.currentRound.amountOfTries").value(game.getCurrentRound().getTries().size()))
                .andExpect(jsonPath("$.currentScore").value(game.getScore()));
    }

    @Test
    void shouldGetGame() throws Exception {
        List<Round> rounds = new ArrayList<Round>();
        Word word = Word.builder().value("testen").build();
        Round round = Round.builder().id((long) 2).tries(new ArrayList<Try>()).word(word).build();
        rounds.add(round);
        Game game = Game.builder().id((long) 1).gameStatus(GameStatus.FINISHED)
                .createdAt(new Timestamp(System.currentTimeMillis())).score(1)
                .rounds(rounds).build();
        final Long gameId = 1L;

        Mockito.when(gameProcessor.find(1L)).thenReturn(java.util.Optional.ofNullable(game));

        mockMvc.perform(get("/game/{id}", gameId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(game.getId()))
                .andExpect(jsonPath("$.gameStatus").value(game.getGameStatus().name()))
                .andExpect(jsonPath("$.currentRound.roundId").value(game.getCurrentRound().getId()))
                .andExpect(jsonPath("$.currentRound.worthLength").value(game.getCurrentRound().getWord().getValue().length()))
                .andExpect(jsonPath("$.currentRound.amountOfTries").value(game.getCurrentRound().getTries().size()))
                .andExpect(jsonPath("$.currentScore").value(game.getScore()));
    }

    @Test
    void fetchNonExistingId() throws Exception {
        final Long gameId = 1L;
        Mockito.when(gameProcessor.find(gameId)).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/game/{id}", gameId))
                .andExpect(status().isNotFound());
    }

    @Test
    void topShouldReturnGames() throws Exception {
        List<Game> games = new ArrayList<Game>();
        games.add(Game.builder().id(1L).score(2).build());
        games.add(Game.builder().id(2L).score(3).build());
        games.add(Game.builder().id(3L).score(4).build());

        Mockito.when(gameProcessor.top10()).thenReturn(games);

        mockMvc.perform(get("/game/top"))
                .andExpect(status().isOk());
    }
}