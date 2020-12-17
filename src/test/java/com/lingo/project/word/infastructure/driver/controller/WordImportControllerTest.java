package com.lingo.project.word.infastructure.driver.controller;

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

import com.lingo.project.game.infastructure.driver.controller.GameController;
import com.lingo.project.word.core.application.WordProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = WordImportController.class)
@ActiveProfiles("test")
class WordImportControllerTest {

    @MockBean
    WordProcessor wordProcessor;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createWordTest() throws Exception {
        String word = "testen";

        mockMvc.perform(post("/word/{word}", word))
                .andExpect(status().isOk());
    }
}