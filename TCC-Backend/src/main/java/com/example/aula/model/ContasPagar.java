package com.example.aula.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class ContasPagar
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaContas categoria;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private CadastroFornecedores fornecedor;

    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres.")
    private String descricao;

    @Column(nullable = false)
    private LocalDate dataVencimento;

    @NotNull(message = "O valor é obrigatório.")
    @DecimalMin(value = "0.0", inclusive = false, message = "O valor deve ser maior que zero.")
    private Double valor;

    private LocalDate dataPagamento;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPagamento statusPagamento;

    public ContasPagar()
    {
        
    }

    public ContasPagar(Long id, CategoriaContas categoria, CadastroFornecedores fornecedor,
            @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres.") String descricao,
            LocalDate dataVencimento,
            @NotNull(message = "O valor é obrigatório.") @DecimalMin(value = "0.0", inclusive = false, message = "O valor deve ser maior que zero.") Double valor,
            LocalDate dataPagamento, StatusPagamento statusPagamento)
    {
        this.id = id;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.statusPagamento = statusPagamento;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public CategoriaContas getCategoria()
    {
        return categoria;
    }

    public void setCategoria(CategoriaContas categoria)
    {
        this.categoria = categoria;
    }

    public CadastroFornecedores getFornecedor()
    {
        return fornecedor;
    }

    public void setFornecedor(CadastroFornecedores fornecedor)
    {
        this.fornecedor = fornecedor;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public LocalDate getDataVencimento()
    {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento)
    {
        this.dataVencimento = dataVencimento;
    }

    public Double getValor()
    {
        return valor;
    }

    public void setValor(Double valor)
    {
        this.valor = valor;
    }

    public LocalDate getDataPagamento()
    {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento)
    {
        this.dataPagamento = dataPagamento;
    }

    public StatusPagamento getStatusPagamento()
    {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento)
    {
        this.statusPagamento = statusPagamento;
    }

    
}
