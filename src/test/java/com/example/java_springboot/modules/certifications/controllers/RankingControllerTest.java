package com.example.java_springboot.modules.certifications.controllers;

import com.example.java_springboot.modules.certifications.useCases.Top10RankingUseCase;
import com.example.java_springboot.modules.students.entities.CertificationStudentEntity;
import com.example.java_springboot.modules.students.repositories.CertificationStudentRepository;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RankingController.class)
public class RankingControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private Top10RankingUseCase top10RankingUseCase;

        @MockBean
        private CertificationStudentRepository certificationStudentRepository;

        @Test
        public void testGetTop10Ranking() throws Exception {
                List<CertificationStudentEntity> mockRanking = List.of(
                                CertificationStudentEntity.builder()
                                                .id(UUID.randomUUID())
                                                .technology("Java")
                                                .grade(90)
                                                .studentID(UUID.randomUUID())
                                                .build(),
                                CertificationStudentEntity.builder()
                                                .id(UUID.randomUUID())
                                                .technology("Python")
                                                .grade(85)
                                                .studentID(UUID.randomUUID())
                                                .build());

                when(top10RankingUseCase.execute()).thenReturn(mockRanking);

                mockMvc.perform(get("/ranking/top10"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", hasSize(2)))
                                .andExpect(jsonPath("$[0].technology", is("Java")))
                                .andExpect(jsonPath("$[0].grade", is(90)))
                                .andExpect(jsonPath("$[1].technology", is("Python")))
                                .andExpect(jsonPath("$[1].grade", is(85)));
        }
}
