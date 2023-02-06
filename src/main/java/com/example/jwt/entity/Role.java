package com.example.jwt.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
public class Role {

    public Role(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERole name;


    public ERole getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}