package com.example.jwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Abbonamenti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAbbonamento;

    private String nomeAbbonamento;

    private Double costoAbbonammento;



}
