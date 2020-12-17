package com.lingo.project.game.infastructure.driver.controller;


import com.lingo.project.game.core.application.RoundProcessor;
import com.lingo.project.game.core.domain.Round;
import com.lingo.project.game.core.ports.resource.TryResource;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/round")
@AllArgsConstructor
public class RoundController {

    public final RoundProcessor roundProcessor;

    @PutMapping(path = "/{roundId}/try/{word}")
    public ResponseEntity<?> tryWordOnRound(@PathVariable Long roundId, @PathVariable String word) {
        Optional<Round> optionalRound = this.roundProcessor.find(roundId);

        if (optionalRound.isPresent()) {
            Round round = optionalRound.get();
            if (!round.canAnswer()) {
                return new ResponseEntity<>("You cant answer for this round anymore", HttpStatus.BAD_REQUEST);
            } else if (!round.reactedInTime()) {
                this.roundProcessor.endGame(round.getGame());
                return new ResponseEntity<>("You where to slow with answering", HttpStatus.BAD_REQUEST);
            }

            TryResource tryResource = this.roundProcessor.validateWord(round, word);

            return new ResponseEntity<>(tryResource, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
