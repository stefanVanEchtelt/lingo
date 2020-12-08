package com.lingo.project.word.infastructure.driven.database;

import com.lingo.project.word.core.ports.WordWriter;
import org.springframework.stereotype.Service;

@Service
public class LingoWordWriter implements WordWriter {

    @Override
    public boolean writeWord(String word) {
        return false;
    }
}
