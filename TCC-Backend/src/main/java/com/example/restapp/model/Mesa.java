package com.example.restapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMesa;

    @Column(nullable = false)
    private Integer capacidade;
    
    @Column(nullable = false, length = 20)
    private String status;

    
}
