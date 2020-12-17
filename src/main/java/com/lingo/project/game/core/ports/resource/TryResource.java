package com.lingo.project.game.core.ports.resource;

import com.lingo.project.game.core.domain.Game;
import com.lingo.project.word.core.domain.WordFeedback;
import lombok.Getter;

public class TryResource {
    @Getter
    private GameResource game;
    @Getter
    private WordFeedback wordFeedback;

    public TryResource(Game game, WordFeedback wordFeedback) {
        this.game = new GameResource(game);
        this.wordFeedback = wordFeedback;
    }
}
