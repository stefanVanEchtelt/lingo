package com.lingo.project.word.infastructure.driven.database;

import com.lingo.project.word.core.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WordRepository extends JpaRepository<Word, Long> {
    boolean existsByValue(String value);

    @Query(value = "select * from word where LENGTH(value) = :wordLength order by RAND() limit 1", nativeQuery = true)
    Word findRandomByWordLength(@Param("wordLength") int length);
}
