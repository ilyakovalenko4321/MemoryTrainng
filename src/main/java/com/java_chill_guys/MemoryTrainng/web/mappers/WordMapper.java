package com.java_chill_guys.MemoryTrainng.web.mappers;

import com.java_chill_guys.MemoryTrainng.domain.word.Word;
import com.java_chill_guys.MemoryTrainng.web.dto.WordDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WordMapper {

    List<Word> toEntity(List<WordDto> dto);

    List<WordDto> toDto(List<Word> words);

}
