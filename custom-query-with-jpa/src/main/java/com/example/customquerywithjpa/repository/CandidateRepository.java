package com.example.customquerywithjpa.repository;

import com.example.customquerywithjpa.dto.CandidatePDFFile;
import com.example.customquerywithjpa.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    // Inner Join
    @Query("SELECT new com.example.customquerywithjpa.dto.CandidatePDFFile(c.firstName, c.lastName, p.pdfFile) from Candidate c INNER JOIN PDFFile p ON (c.id=p.idCandidate) WHERE c.id =:candidateId")
    List<CandidatePDFFile> fetchCandidateAndPDFFIleByCandidateId(@Param("candidateId") long candidateId);

    // Left outer join
    @Query("SELECT new com.example.customquerywithjpa.dto.CandidatePDFFile(c.firstName, c.lastName, p.pdfFile) from Candidate c LEFT OUTER JOIN PDFFile p ON (c.id=p.idCandidate) WHERE c.id =:candidateId")
    List<CandidatePDFFile> fetchCandidateFirstNameLastNameAndItsPDFFileIfExists(@Param("candidateId") long candidateId);
}