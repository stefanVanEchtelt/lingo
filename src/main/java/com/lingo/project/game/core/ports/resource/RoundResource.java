package com.lingo.project.game.core.ports.resource;

import com.lingo.project.game.core.domain.Round;
import com.lingo.project.game.core.domain.Try;
import lombok.Data;

import java.util.List;

@Data
public class RoundResource {
    private int worthLength;
    private int amountOfTries;
    private List<Try> tries;

    public RoundResource(Round round) {
        this.worthLength = round.getWord().getValue().length();
        this.tries = round.getTries();
        this.amountOfTries = round.getTries().size();
    }
}
