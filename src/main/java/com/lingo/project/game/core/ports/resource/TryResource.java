package com.lingo.project.game.core.ports.resource;

import com.lingo.project.game.core.domain.Game;
import com.lingo.project.word.core.domain.WordFeedback;
import lombok.Data;

@Data
public class TryResource {
    private GameResource game;
    private WordFeedback wordFeedback;

    public TryResource(Game game, WordFeedback wordFeedback) {
        this.game = new GameResource(game);
        this.wordFeedback = wordFeedback;
    }
}
