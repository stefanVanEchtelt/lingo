package com.lingo.project.game.infastructure.driver.controller;

import com.lingo.project.game.core.application.GameProcessor;
import com.lingo.project.game.core.domain.Game;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@RestController
@RequestMapping("/game")
public class GameController {

    public final GameProcessor gameProcessor;

    public GameController(GameProcessor gameProcessor) {
        this.gameProcessor = gameProcessor;
    }

    @PostMapping(path = "/start")
    public ResponseEntity<Game> start() {
        Game game = this.gameProcessor.start();
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Game> add(@PathVariable Long id) {
        Optional<Game> game = this.gameProcessor.find(id);

        if (game.isPresent()) {
            return new ResponseEntity<>(game.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
