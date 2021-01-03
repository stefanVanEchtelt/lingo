package com.lingo.project.word.infastructure.driven.file;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TxtFileWordReaderTest {

    @Test
    public void testReadWords() {
        TxtFileWordReader txtFileWordReader = new TxtFileWordReader();
        assertSame (txtFileWordReader.readWords().getClass().getName(), "java.util.stream.ReferencePipeline$Head");
    }
}