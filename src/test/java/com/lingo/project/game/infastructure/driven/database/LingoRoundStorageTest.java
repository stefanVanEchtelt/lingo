package com.lingo.project.game.infastructure.driven.database;

import com.lingo.project.game.core.domain.Round;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LingoRoundStorageTest {
    @Test
    void testSave() {
        Round round = new Round();
        RoundRepository roundRepository = mock(RoundRepository.class);
        when(roundRepository.save(round)).thenReturn(round);

        LingoRoundStorage lingoRoundStorage = new LingoRoundStorage(roundRepository);
        Round result = lingoRoundStorage.save(round);

        assertEquals(round, result);
    }
}