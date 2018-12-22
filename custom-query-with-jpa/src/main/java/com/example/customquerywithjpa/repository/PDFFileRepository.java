package com.example.customquerywithjpa.repository;

import com.example.customquerywithjpa.entity.PDFFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PDFFileRepository extends JpaRepository<PDFFile, Long> {

}
