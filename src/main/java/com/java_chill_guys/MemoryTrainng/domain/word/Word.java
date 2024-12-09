package com.java_chill_guys.MemoryTrainng.domain.word;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "words")
public class Word {

    @Id
    private Long id;
    private String word;

}
