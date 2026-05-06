package com.example.restapp.entity.principal;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.restapp.entity.Usuario;
import com.example.restapp.entity.financeiro.MetodoPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamento")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "mesa_sessao_id", nullable = false)
    private MesaSessao mesaSessao;

    private BigDecimal valorPagamento;

    @ManyToOne
    @JoinColumn(name = "metodo_pagamento_id", nullable = false)
    private MetodoPagamento metodoPagamento;

    @CreatedDate
    private LocalDateTime horarioLancamento;
}
