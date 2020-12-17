package com.lingo.project.game.core.ports.resource;

import com.lingo.project.game.core.domain.Round;
import lombok.Data;
import lombok.Getter;

import java.util.Objects;

@Getter
public class RoundResource {
    private Long roundId;
    private int worthLength;
    private int amountOfTries;

    public RoundResource(Round round) {
        this.roundId = round.getId();
        this.worthLength = round.getWord().getValue().length();
        this.amountOfTries = round.getTries().size();
    }

    @Override
    public boolean equals(Object o){
        // self check
        if(this == o){ return true; } else
            // null check
            if(o == null){ return false;} else
                // type check and cast
                if(getClass() != o.getClass()){ return false; } else {
                    final RoundResource a = (RoundResource) o;
                    // field comparison
                    return Objects.equals(a, a);
                }
    }
}
