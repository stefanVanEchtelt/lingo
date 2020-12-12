package com.lingo.project.word.core.ports;

import java.util.stream.Stream;

public interface WordReader {
    Stream<String> readWords();
}
