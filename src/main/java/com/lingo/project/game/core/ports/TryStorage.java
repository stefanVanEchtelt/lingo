package com.lingo.project.game.core.ports;

import com.lingo.project.game.core.domain.Game;
import com.lingo.project.game.core.domain.Try;

public interface TryStorage {
    public Try create(Try t);
}
