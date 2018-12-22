package com.example.customquerywithjpa.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "eupass_pdffile")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PDFFile implements Serializable {

    private static final long serialVersionUID = -753514667628201960L;

    @Id
    private long id;
    private byte[] pdfFile;
    private Long idCandidate;
}