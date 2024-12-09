package com.java_chill_guys.MemoryTrainng.web.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class WordDto {

    @NotNull
    @NotEmpty
    private List<@NotNull String> words;

}
