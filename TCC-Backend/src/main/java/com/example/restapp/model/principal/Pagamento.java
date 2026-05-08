package com.example.restapp.model.principal;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.restapp.model.Usuario;
import com.example.restapp.model.financeiro.MetodoPagamento;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(nullable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private MesaSessao mesaSessao;

    @JoinColumn(nullable = false, updatable = false)
    private BigDecimal valorPagamento;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private MetodoPagamento metodoPagamento;

    @CreatedDate
    @JoinColumn(nullable = false, updatable = false)
    private LocalDateTime horarioLancamento;
}
