package com.example.customquerywithjpa.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "eupass_candidate")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Candidate implements Serializable {

    @Id
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private String munic;
    private String postalCode;
}