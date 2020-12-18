package com.lingo.project.game.infastructure.driven.database;

import com.lingo.project.game.core.domain.Try;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LingoTryStorageTest {
    @Test
    void beforeAll() {
        Try tryInst = Try.builder().value("testen").build();
        TryRepository tryRepository = mock(TryRepository.class);
        when(tryRepository.save(tryInst)).thenReturn(tryInst);

        LingoTryStorage lingoTryStorage = new LingoTryStorage(tryRepository);
        Try tryResult = lingoTryStorage.create(tryInst);

        assertEquals(tryInst, tryResult);
    }
}