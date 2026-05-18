package com.example.restapp.model.principal;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.restapp.model.Usuario;
import com.example.restapp.model.enums.StatusMesa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // Indica que a classe é uma entidade JPA
@EntityListeners(AuditingEntityListener.class) // Habilita o listener de auditoria para o @CreatedBy e @CreatedDate funcionarem
@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class MesaSessao
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false, updatable = false)
    @ManyToOne
    @CreatedBy
    private Usuario atendenteAbertura;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime horarioAbertura;

    @ManyToOne(optional = true)
    @JoinColumn(name = "atendente_responsavel_id", nullable = true, updatable = true)
    @JsonManagedReference
    private Usuario atendenteResponsavel;

    @ManyToOne
    @JoinColumn(name = "numero_mesa", nullable = false)
    private Mesa mesa;

    private Integer quantidadePessoas;

    @ManyToOne(optional = true)
    @JoinColumn(name = "cliente_id", nullable = true)
    @JsonManagedReference
    @JsonIgnore
    private Cliente cliente;
    
    @Enumerated(EnumType.STRING)
    private StatusMesa status;

    private LocalDateTime horarioFechamento;

    // @OneToMany(mappedBy = "mesaSessao", orphanRemoval = true)
    // private List<Pedido> pedidos = new ArrayList<>();

    // @OneToMany(mappedBy = "mesaSessao", orphanRemoval = true)
    // private List<Pagamento> pagamentos = new ArrayList<>();

    private BigDecimal valorTotalMesa;

    private BigDecimal taxaServico;

    private BigDecimal valorTotalMesaServico; // É para ser uma soma de valorTotalMesa + taxaServico

    @PreUpdate
    public void aoAtualizar()
    {
        if (this.status == StatusMesa.FECHAMENTO)
        {
            this.horarioFechamento = LocalDateTime.now();
        }
    }
    
}