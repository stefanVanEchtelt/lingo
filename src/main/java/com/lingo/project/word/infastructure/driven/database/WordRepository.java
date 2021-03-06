package com.lingo.project.word.infastructure.driven.database;

import com.lingo.project.word.core.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    boolean existsByValue(String value);

    @Query(value = "select * from word where LENGTH(value) = :wordLength and is_deleted = false order by random() limit 1", nativeQuery = true)
    Word findRandomByWordLength(@Param("wordLength") int length);
}
