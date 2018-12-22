package com.example.customquerywithjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class CandidatePDFFile {

    private String firstName;
    private String lastName;
    private byte[] pdfFile;
}
