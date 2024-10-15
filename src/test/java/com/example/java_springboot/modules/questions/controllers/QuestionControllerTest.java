package com.example.java_springboot.modules.questions.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import com.example.java_springboot.modules.questions.dto.QuestionResultDTO;
import com.example.java_springboot.modules.questions.useCases.QuestionUseCase;

@WebMvcTest(QuestionController.class)
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionUseCase questionUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnQuestionsByTechnology() throws Exception {
        QuestionResultDTO question1 = QuestionResultDTO.builder()
                .id(UUID.randomUUID())
                .technology("Java")
                .description("O que é Java?")
                .alternatives(Arrays.asList())
                .build();

        QuestionResultDTO question2 = QuestionResultDTO.builder()
                .id(UUID.randomUUID())
                .technology("Java")
                .description("Explique o conceito de JIT no Java.")
                .alternatives(Arrays.asList())
                .build();

        List<QuestionResultDTO> mockQuestions = Arrays.asList(question1, question2);

        when(questionUseCase.findByTechnology("Java")).thenReturn(mockQuestions);

        mockMvc.perform(get("/questions/technology/Java")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].technology").value("Java"))
                .andExpect(jsonPath("$[0].description").value("O que é Java?"))
                .andExpect(jsonPath("$[1].technology").value("Java"))
                .andExpect(jsonPath("$[1].description").value("Explique o conceito de JIT no Java."));
    }
}
