package com.example.java_springboot.modules.questions.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_springboot.modules.questions.dto.QuestionResultDTO;
import com.example.java_springboot.modules.questions.useCases.QuestionUseCase;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionUseCase questionUseCase;

    @GetMapping("/technology/{technology}")
    public List<QuestionResultDTO> findByTechnology(@PathVariable String technology) {

        return questionUseCase.findByTechnology(technology);
    }
}
