package com.example.aula.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class MetodoPagamento
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do método de pagamento é obrigatório.")
    private String nomeMetodoPagamento;

    @Enumerated(EnumType.STRING)
    private TipoMetodoPagamento tipoMetodoPagamento;
    
    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeira; // Pode ser null para métodos não-cartão

    public MetodoPagamento()
    {
        
    }

    public MetodoPagamento(Long id,
            @NotBlank(message = "O nome do método de pagamento é obrigatório.") String nomeMetodoPagamento,
            TipoMetodoPagamento tipoMetodoPagamento, BandeiraCartao bandeira)
    {
        this.id = id;
        this.nomeMetodoPagamento = nomeMetodoPagamento;
        this.tipoMetodoPagamento = tipoMetodoPagamento;
        this.bandeira = bandeira;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNomeMetodoPagamento()
    {
        return nomeMetodoPagamento;
    }

    public void setNomeMetodoPagamento(String nomeMetodoPagamento)
    {
        this.nomeMetodoPagamento = nomeMetodoPagamento;
    }

    public TipoMetodoPagamento getTipoMetodoPagamento()
    {
        return tipoMetodoPagamento;
    }

    public void setTipoMetodoPagamento(TipoMetodoPagamento tipoMetodoPagamento)
    {
        this.tipoMetodoPagamento = tipoMetodoPagamento;
    }

    public BandeiraCartao getBandeira()
    {
        return bandeira;
    }

    public void setBandeira(BandeiraCartao bandeira)
    {
        this.bandeira = bandeira;
    }
    
}
