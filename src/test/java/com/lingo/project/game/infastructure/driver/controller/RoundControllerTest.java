package com.lingo.project.game.infastructure.driver.controller;

import com.lingo.project.game.core.application.RoundProcessor;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RoundController.class)
@ActiveProfiles("test")
class RoundControllerTest {

    @MockBean
    RoundProcessor roundProcessor;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void tryWordOnNonExistingRound() throws Exception {
        final Long roundId = 1L;

        Mockito.when(roundProcessor.find(roundId)).thenReturn(Optional.empty());

        mockMvc.perform(put("/round/{roundId}/try/testen", roundId))
                .andExpect(status().isNotFound());
    }

    @Test
    void tryWordWhenAlreadyWon() throws Exception {
        final Long roundId = 1L;
        Word word = Word.builder().value("testen").build();
        List<Try> tries = new ArrayList<Try>();
        Round round = Round.builder().word(word).build();
        tries.add(Try.builder().value("testen").round(round).build());
        round.setTries(tries);

        Mockito.when(roundProcessor.find(roundId)).thenReturn(Optional.of(round));

        mockMvc.perform(put("/round/{roundId}/try/testen", roundId))
                .andExpect(status().isBadRequest());
    }

    @Test
    void tryWordToLate() throws Exception {
        final Long roundId = 1L;
        List<Try> tries = new ArrayList<Try>();
        Word word = Word.builder().value("testen").build();
        Round round = Round.builder().word(word).build();
        tries.add(Try.builder().value("tester").createdAt(new Timestamp(System.currentTimeMillis() - 11000)).round(round).build());
        round.setTries(tries);

        Mockito.when(roundProcessor.find(roundId)).thenReturn(Optional.of(round));

        mockMvc.perform(put("/round/{roundId}/try/testen", roundId))
                .andExpect(status().isBadRequest());
    }
}