package com.lingo.project.word.infastructure.driver.controller;

import com.lingo.project.word.core.application.WordProcessor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("word")
public class WordImportController {

    public final WordProcessor wordProcessor;

    public WordImportController(WordProcessor wordProcessor) {
        this.wordProcessor = wordProcessor;
    }

    @PostMapping(path = "/{word}")
    public void add(@PathVariable String word) {
        this.wordProcessor.addSingle(word);
    }
}
