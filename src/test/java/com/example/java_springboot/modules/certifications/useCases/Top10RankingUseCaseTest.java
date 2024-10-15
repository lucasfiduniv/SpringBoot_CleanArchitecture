package com.example.java_springboot.modules.certifications.useCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.java_springboot.modules.students.entities.CertificationStudentEntity;
import com.example.java_springboot.modules.students.repositories.CertificationStudentRepository;

class Top10RankingUseCaseTest {

    @InjectMocks
    private Top10RankingUseCase top10RankingUseCase;

    @Mock
    private CertificationStudentRepository certificationStudentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnTop10StudentsByGrade() {
        CertificationStudentEntity student1 = CertificationStudentEntity.builder()
                .id(UUID.randomUUID())
                .technology("Java")
                .grade(95)
                .studentID(UUID.randomUUID())
                .build();

        CertificationStudentEntity student2 = CertificationStudentEntity.builder()
                .id(UUID.randomUUID())
                .technology("Python")
                .grade(90)
                .studentID(UUID.randomUUID())
                .build();

        List<CertificationStudentEntity> mockCertifications = Arrays.asList(student1, student2);

        when(certificationStudentRepository.findTop10ByOrderByGradeDesc()).thenReturn(mockCertifications);

        List<CertificationStudentEntity> result = top10RankingUseCase.execute();

        assertEquals(2, result.size());
        assertEquals(95, result.get(0).getGrade());
        assertEquals(90, result.get(1).getGrade());
    }
}
