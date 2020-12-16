package com.lingo.project.word.infastructure.driven.file;

import com.lingo.project.word.core.ports.WordReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
@Slf4j
public class TxtFileWordReader implements WordReader {
	private final Path source;

	public TxtFileWordReader() {
		this.source = Paths.get("./startup.txt");
	}

	@Override
	public Stream<String> readWords() {
		try {
			log.info("Reading words from file {}", this.source);
			return Files.lines(this.source);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
