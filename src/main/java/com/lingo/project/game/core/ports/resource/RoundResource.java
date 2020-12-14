package com.lingo.project.game.core.ports.resource;

import com.lingo.project.game.core.domain.Round;
import lombok.Data;

@Data
public class RoundResource {
    private Long roundId;
    private int worthLength;
    private int amountOfTries;

    public RoundResource(Round round) {
        this.worthLength = round.getWord().getValue().length();
        this.amountOfTries = round.getTries().size();
        this.roundId = round.getId();
    }
}
