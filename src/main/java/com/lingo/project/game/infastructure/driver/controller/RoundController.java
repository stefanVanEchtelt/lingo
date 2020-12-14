package com.lingo.project.game.infastructure.driver.controller;

import com.lingo.project.game.core.application.GameProcessor;
import com.lingo.project.game.core.application.RoundProcessor;
import com.lingo.project.game.core.domain.Game;
import com.lingo.project.game.core.domain.Round;
import com.lingo.project.word.core.domain.WordFeedback;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/round")
public class RoundController {

    public final RoundProcessor roundProcessor;

    public RoundController(RoundProcessor roundProcessor) {
        this.roundProcessor = roundProcessor;
    }

    @PutMapping(path = "/{roundId}/try/{word}")
    public ResponseEntity<?> tryWordOnRound(@PathVariable Long roundId, @PathVariable String word) {
        Optional<Round> optionalRound = this.roundProcessor.find(roundId);

        if (optionalRound.isPresent()) {
            WordFeedback wordFeedback = this.roundProcessor.validate(optionalRound.get(), word);
            return new ResponseEntity<>(wordFeedback, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
