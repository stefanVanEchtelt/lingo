package com.lingo.project.word.infastructure.driver.controller;

import com.lingo.project.word.core.application.WordProcessor;
import com.lingo.project.word.core.domain.Word;
import com.lingo.project.word.infastructure.driven.database.LingoWordStorage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("word")
@AllArgsConstructor
public class WordImportController {

    public final WordProcessor wordProcessor;

    @PostMapping(path = "/{word}")
    public void add(@PathVariable String word) {
        this.wordProcessor.addSingle(word);
    }
}
