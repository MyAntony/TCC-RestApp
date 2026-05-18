package com.example.restapp.model.principal;

import java.math.BigDecimal;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.restapp.model.Usuario;
import com.example.restapp.model.produtos.Produto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // Indica que a classe é uma entidade JPA
@EntityListeners(AuditingEntityListener.class) // Habilita o listener de auditoria
@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class Pedido
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedBy
    @ManyToOne /*(fetch = FetchType.LAZY)*/
    @JoinColumn(nullable = false, updatable = false)
    private Usuario usuario;
    
    // @JsonBackReference
    @ManyToOne /*(fetch = FetchType.LAZY)*/
    @JoinColumn(name = "mesaSessao_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MesaSessao mesaSessao;

    private String descricaoPedido;

    @ManyToOne /*(fetch = FetchType.LAZY)*/
    @JoinColumn(nullable = false)
    private Produto produto;

    private Integer quantidadeProduto = 1;

    @Column(nullable = false)
    private BigDecimal valorUnitario;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private java.time.LocalDateTime horarioLancamento;
}
