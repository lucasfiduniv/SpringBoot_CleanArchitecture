package com.example.java_springboot.modules.questions.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResultDTO {
    private UUID id;
    private String technology;
    private String description;

    private List<AlternativesResultDTO> alternatives;
}
