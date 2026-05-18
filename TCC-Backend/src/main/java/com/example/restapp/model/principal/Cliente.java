package com.example.restapp.model.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.restapp.model.Endereco;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Entity
@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class Cliente
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false, unique = true)
    private UUID idAuxiliar = UUID.randomUUID();

    @Column(nullable = false)
    private String nome;

    @Column(unique = true)
    private String cpf;

    private String telefone;

    @Column(unique = true)
    private String email;

    // @JsonManagedReference
    // @OneToMany(mappedBy = "cliente")
    // private List<Mesa> mesas= new ArrayList<>();

    // @ElementCollection
    // // @CollectionTable(name = "cliente_endereco", joinColumns = @JoinColumn(name = "cliente_id"))
    // private List<Endereco> enderecos = new ArrayList<>();
}