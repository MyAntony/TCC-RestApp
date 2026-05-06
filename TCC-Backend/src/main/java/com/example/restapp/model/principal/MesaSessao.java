package com.example.restapp.entity.principal;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.restapp.entity.Usuario;
import com.example.restapp.entity.enums.StatusMesa;

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

    @JoinColumn(name = "atendente_abertura_id", nullable = false, updatable = false)
    @ManyToOne
    @CreatedBy
    private Usuario atendenteAbertura;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime horarioAbertura;

    @ManyToOne
    @JoinColumn(name = "atendente_responsavel_id")
    private Usuario atendenteResponsavel;

    @ManyToOne
    @JoinColumn(name = "numero_mesa", nullable = false)
    private Mesa mesa;

    private Integer quantidadePessoas;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @Enumerated(EnumType.STRING)
    private StatusMesa status;

    private LocalDateTime horarioFechamento;

    // @OneToMany(mappedBy = "mesa_sessao", cascade = CascadeType.ALL)
    // private List<Pedido> pedidos;

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