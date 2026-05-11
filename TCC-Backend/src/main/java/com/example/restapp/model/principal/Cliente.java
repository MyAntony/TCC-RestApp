package com.example.restapp.model.principal;

import java.util.UUID;

import com.example.restapp.model.Endereco;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "cpf"))
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

    @Embedded // Indica que o endereço é um campo embutido
    private Endereco endereco;
}