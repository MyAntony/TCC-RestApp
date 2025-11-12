package com.example.restapp.model.principal;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.example.restapp.model.Usuario;
import com.example.restapp.model.enums.StatusMesa;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity // Indica que a classe é uma entidade JPA
@EntityListeners(AuditingEntityListener.class) // Habilita o listener de auditoria para o @CreatedBy e @CreatedDate funcionarem
@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class Mesa
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

    @NotNull(message = "O número da mesa é obrigatório.")
    private Integer numeroMesa;

    private Integer quantidadePessoas;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @Enumerated(EnumType.STRING)
    private StatusMesa status;

    private LocalDateTime horarioFechamento;

    @OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos;

    private Double valorTotalMesa;

    private Double valorTotalMesaServico;

    @PreUpdate
    public void aoAtualizar()
    {
        if (this.status == StatusMesa.FECHAMENTO)
        {
            this.horarioFechamento = LocalDateTime.now();
        }
    }
    
}
