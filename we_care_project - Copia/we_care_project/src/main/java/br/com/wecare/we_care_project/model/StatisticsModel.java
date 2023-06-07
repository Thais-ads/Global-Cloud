package br.com.wecare.we_care_project.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "TB_WE_IBGE")
public class StatisticsModel implements Serializable {

    private static final long serialVErsionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long Id;

    @Column(nullable = false, unique = true)
    private String city;

    @Column(nullable = false)
    private float pib;

    @Column(nullable = false)
    private float workingForce;

    @Column(nullable = false)
    private float educationLevel;

    @Column(nullable = false)
    private float poverty;

    @Column(nullable = false)
    private float salary;

    @Column(nullable = false)
    private float povertyPercentage;

    @Column(nullable = false)
    private LocalDate registrationDate;


}
