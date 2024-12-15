package com.java_chill_guys.MemoryTrainng.repository;

import com.java_chill_guys.MemoryTrainng.domain.word.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {

    @Query(value = """
            SELECT *
            FROM words w
            WHERE LENGTH(w.word) = :length
            ORDER BY RANDOM()
            LIMIT :number;
            """, nativeQuery = true)
    List<Word> selectNumOfWords(@Param("number") Integer number, @Param("length") Integer length);

}
