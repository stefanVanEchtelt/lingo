package com.lingo.project.game.infastructure.driver.controller;

import com.lingo.project.game.core.application.GameProcessor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("game")
public class GameController {

    public final GameProcessor gameProcessor;

    public GameController(GameProcessor gameProcessor) {
        this.gameProcessor = gameProcessor;
    }

    @PostMapping(path = "/{word}")
    public void add(@PathVariable String word) {
        this.gameProcessor.start();
    }
}
