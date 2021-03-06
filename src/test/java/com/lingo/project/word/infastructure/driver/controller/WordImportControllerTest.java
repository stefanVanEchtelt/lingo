package com.lingo.project.word.infastructure.driver.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.lingo.project.word.core.application.WordProcessor;

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