package com.example.jwt.service;


import com.example.jwt.entity.Abbonamenti;
import com.example.jwt.entity.User;
import com.example.jwt.repository.AbbonamentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbbonamentiService {
    @Autowired
    private AbbonamentiRepository abbonamentiRepository;


    public Abbonamenti save(Abbonamenti abbonamenti){
        return abbonamentiRepository.save(abbonamenti);
    }

    public List<Abbonamenti> findAll(){
        return abbonamentiRepository.findAll();
    }

    public List<Abbonamenti> findAllByString(String var){
        return abbonamentiRepository.findAllByNomeAbbonamentoContains(var);
    }

    public Page<Abbonamenti> getUserPagination(Integer pageNumber, Integer pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "nomeAbbonamento");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return abbonamentiRepository.findAll(pageable);
    }


}
