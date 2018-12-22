package com.example.customquerywithjpa.repository;

import com.example.customquerywithjpa.dto.CandidatePDFFile;
import com.example.customquerywithjpa.entity.Candidate;
import com.example.customquerywithjpa.entity.PDFFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class CandidateRepositoryTest {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PDFFileRepository pdfFileRepository;

    @BeforeEach
    void setUp() {
        candidateRepository.save(Candidate.builder().id(1L).firstName("sham").lastName("bhand").address("India").munic("munic").postalCode("411057").build());
        candidateRepository.save(Candidate.builder().id(2L).firstName("abhijeet").lastName("gulve").address("India").munic("munic").postalCode("422605").build());
        candidateRepository.save(Candidate.builder().id(3L).firstName("krishna").lastName("pandey").address("India").munic("munic").postalCode("411033").build());
        candidateRepository.save(Candidate.builder().id(4L).firstName("rushi").lastName("sonawane").address("India").munic("munic").postalCode("422608").build());

        pdfFileRepository.save(PDFFile.builder().id(1L).idCandidate(2L).pdfFile("abhi_gulve_pdf1".getBytes()).build());
        pdfFileRepository.save(PDFFile.builder().id(2L).idCandidate(2L).pdfFile("abhi_gulve_pdf2".getBytes()).build());
        pdfFileRepository.save(PDFFile.builder().id(3L).idCandidate(3L).pdfFile("kc_pandey_pdf1".getBytes()).build());
    }

    private static Stream<Object[]> data() {
        return Stream.of(new Object[][]{
                {-100L, Collections.emptyList(), "test when candidate does not exist"},
                {2L, Arrays.asList(CandidatePDFFile.builder().firstName("abhijeet").lastName("gulve").pdfFile("abhi_gulve_pdf1".getBytes()).build(), CandidatePDFFile.builder().firstName("abhijeet").lastName("gulve").pdfFile("abhi_gulve_pdf2".getBytes()).build()), "test when candidate have more than one pdf"},
                {3L, Arrays.asList(CandidatePDFFile.builder().firstName("krishna").lastName("pandey").pdfFile("kc_pandey_pdf1".getBytes()).build()), "test when candidate have single pdf"},
                {4L, Collections.emptyList(), "test when candidate is exist but it does not have any pdf"}
        });
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("data")
    void testFetchDataBetweenCandidateAndPDFFileByCandidateId(final long candidateId, final List<CandidatePDFFile> expectedCandidatePDFFiles, final String description) {

        // when
        final List<CandidatePDFFile> result = candidateRepository.fetchCandidateAndPDFFIleByCandidateId(candidateId);

        // then
        assertThat(result).isEqualTo(expectedCandidatePDFFiles);
    }


    private static Stream<Object[]> data1() {
        return Stream.of(new Object[][]{
                {-100L, Collections.emptyList(), "test when candidate does not exist"},
                {2L, Arrays.asList(CandidatePDFFile.builder().firstName("abhijeet").lastName("gulve").pdfFile("abhi_gulve_pdf1".getBytes()).build(), CandidatePDFFile.builder().firstName("abhijeet").lastName("gulve").pdfFile("abhi_gulve_pdf2".getBytes()).build()), "test when candidate have more than one pdf"},
                {3L, Arrays.asList(CandidatePDFFile.builder().firstName("krishna").lastName("pandey").pdfFile("kc_pandey_pdf1".getBytes()).build()), "test when candidate have single pdf"},
                {4L, Arrays.asList(CandidatePDFFile.builder().firstName("rushi").lastName("sonawane").pdfFile(null).build()), "test when candidate is exist but ot does not have any pdf"}
        });
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("data1")
    void testFetchDataCandidateFirstNameLastNameAndPDFFileIfItHasAny(final long candidateId, final List<CandidatePDFFile> expectedCandidatePDFFiles, final String description) {
        // when
        final List<CandidatePDFFile> result = candidateRepository.fetchCandidateFirstNameLastNameAndItsPDFFileIfExists(candidateId);

        // then
        assertThat(result).isEqualTo(expectedCandidatePDFFiles);
    }
}