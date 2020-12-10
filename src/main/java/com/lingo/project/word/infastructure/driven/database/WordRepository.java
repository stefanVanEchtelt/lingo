package com.lingo.project.word.infastructure.driven.database;

import com.lingo.project.word.core.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
    boolean existsByValue(String value);
}
