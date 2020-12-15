package com.lingo.project.game.infastructure.driver.controller;

import com.lingo.project.game.core.application.GameProcessor;
import com.lingo.project.game.core.domain.Game;
import com.lingo.project.game.core.ports.resource.GameResource;
import com.lingo.project.game.core.ports.resource.GameResultResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/game")
public class GameController {

    public final GameProcessor gameProcessor;

    public GameController(GameProcessor gameProcessor) {
        this.gameProcessor = gameProcessor;
    }

    @PostMapping(path = "/start")
    public ResponseEntity<GameResource> start() {
        Game game = this.gameProcessor.start();
        return new ResponseEntity<>(new GameResource(game), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GameResource> add(@PathVariable Long id) {
        Optional<Game> game = this.gameProcessor.find(id);

        if (game.isPresent()) {
            return new ResponseEntity<>(new GameResource(game.get()), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/top")
    public ResponseEntity<List<GameResultResource>> highScores()
    {
        List<GameResultResource> gameResourceList = new ArrayList<>();

        for (Game game: this.gameProcessor.top10()) {
            gameResourceList.add(new GameResultResource(game));
        }

        return new ResponseEntity<>(gameResourceList, HttpStatus.OK);
    }
}
