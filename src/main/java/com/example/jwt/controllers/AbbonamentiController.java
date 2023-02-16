package com.example.jwt.controllers;

import com.example.jwt.entity.Abbonamenti;
import com.example.jwt.service.AbbonamentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 //80


@RestController
@RequestMapping("/api/abb")
@PreAuthorize("hasRole('USER')")
public class AbbonamentiController {
    @Autowired
    private AbbonamentiService abbonamentiService;

    @PostMapping("/abbonamenti")
    public Abbonamenti saveAll(@RequestBody Abbonamenti abbonamenti){
        return abbonamentiService.save((Abbonamenti) abbonamenti);
    }

    @GetMapping("/abbonamenti")
    public List<Abbonamenti> getAll(){
        return abbonamentiService.findAll();
    }

    @GetMapping("/abbonamenti/{var}")
    public List<Abbonamenti> getAllByString(@PathVariable String var){
        return abbonamentiService.findAllByString(var);
    }




    @RequestMapping(value = "/pageOf", method = RequestMethod.GET)
    public Page<Abbonamenti> pageOf(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return abbonamentiService.getUserPagination(page, size);
    }


}
