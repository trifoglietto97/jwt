package com.example.jwt.repository;


import com.example.jwt.entity.Abbonamenti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbbonamentiRepository extends JpaRepository<Abbonamenti, Integer> {

    List<Abbonamenti> findAllByNomeAbbonamentoContains(String var);

}
