package com.lingo.project.word.infastructure.driver.controller;

import com.lingo.project.word.core.application.WordProcessor;
import com.lingo.project.word.core.domain.Word;
import com.lingo.project.word.infastructure.driven.database.LingoWordStorage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("word")
public class WordImportController {

    public final WordProcessor wordProcessor;
    public final LingoWordStorage lingoWordStorage;

    public WordImportController(WordProcessor wordProcessor, LingoWordStorage lingoWordStorage) {
        this.wordProcessor = wordProcessor;
        this.lingoWordStorage = lingoWordStorage;
    }

    @PostMapping(path = "/{word}")
    public void add(@PathVariable String word) {
        this.wordProcessor.addSingle(word);
    }

    @GetMapping(path = "/bier/bier")
    public Word add() {
        return this.lingoWordStorage.findRandomWordByLength(6);
    }
}
