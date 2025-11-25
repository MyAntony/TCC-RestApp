package com.example.restapp.init;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.restapp.model.Cargo;
import com.example.restapp.model.Usuario;
import com.example.restapp.model.financeiro.BandeiraCartao;
import com.example.restapp.model.financeiro.MetodoPagamento;
import com.example.restapp.model.financeiro.TipoMetodoPagamento;
// import com.example.restapp.model.financeiro.MetodoPagamento;
import com.example.restapp.model.principal.Mesa;
import com.example.restapp.repository.MesaRepository;
import com.example.restapp.repository.MetodoPagamentoRepository;
import com.example.restapp.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DataInitializer
{

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final MesaRepository mesaRepository;
    private final MetodoPagamentoRepository metodoPagamentoRepository;

    // public DataInitializer(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder, MesaRepository mesaRepository)
    // {
    //     this.usuarioRepository = usuarioRepository;
    //     this.passwordEncoder = passwordEncoder;
    //     this.mesaRepository = mesaRepository;
    // }

    // Método auxiliar para criar e salvar métodos de pagamento
    private MetodoPagamento criarMetodo(String nome, TipoMetodoPagamento tipo, BandeiraCartao bandeira)
    {
        MetodoPagamento metodoPagamento = new MetodoPagamento();
        metodoPagamento.setNomeMetodoPagamento(nome);
        metodoPagamento.setTipoMetodoPagamento(tipo);
        metodoPagamento.setBandeira(bandeira);

        return metodoPagamentoRepository.save(metodoPagamento);
    }

    @PostConstruct
    public void init()
    {
        // Verifica se já existe algum usuário no banco
        if (usuarioRepository.findAll().isEmpty())
        {
            Usuario admin = new Usuario();
            admin.setNome("Administrador");
            admin.setEmail("admin@restaurante.com");
            admin.setSenha(passwordEncoder.encode("123456")); // Senha inicial
            admin.setCargo(Cargo.ADMINISTRADOR);

            usuarioRepository.save(admin);
            System.out.println("Administrador inicial criado com sucesso!");
        }
        
        // Verifica se já existem mesas no banco, no futuro devemos colocar uma configuração para o usuário definir a quantidade de mesas
        if (mesaRepository.count() == 0)
        {
            for (long i = 1; i <= 100; i++) // Tentei usar o Long, mas não funciona, e o int dá conflito por não ser o mesmo que Long
            {
                Mesa mesa = new Mesa();
                mesa.setId(i); // número da mesa = id
                mesaRepository.save(mesa);
            }
            System.out.println("Mesas criadas automaticamente!");
        }

        if (metodoPagamentoRepository.findAll().isEmpty())
        {
            criarMetodo("Dinheiro", TipoMetodoPagamento.DINHEIRO, null);
            criarMetodo("Pix", TipoMetodoPagamento.PIX, null);

            criarMetodo("Cartão de Crédito - American Express", TipoMetodoPagamento.CARTAO_CREDITO, BandeiraCartao.AMERICAN_EXPRESS);
            criarMetodo("Cartão de Crédito - Visa", TipoMetodoPagamento.CARTAO_CREDITO, BandeiraCartao.VISA);
            criarMetodo("Cartão de Crédito - Mastercard", TipoMetodoPagamento.CARTAO_CREDITO, BandeiraCartao.MASTERCARD);
            criarMetodo("Cartão de Crédito - Elo", TipoMetodoPagamento.CARTAO_CREDITO, BandeiraCartao.ELO);
            criarMetodo("Cartão de Crédito - Hipercard", TipoMetodoPagamento.CARTAO_CREDITO, BandeiraCartao.HIPERCARD);
            criarMetodo("Cartão de Crédito - Diners Club", TipoMetodoPagamento.CARTAO_CREDITO, BandeiraCartao.DINERS_CLUB);
            criarMetodo("Cartão de Crédito - Sorocred", TipoMetodoPagamento.CARTAO_CREDITO, BandeiraCartao.SOROCRED);
            criarMetodo("Cartão de Crédito - Aura", TipoMetodoPagamento.CARTAO_CREDITO, BandeiraCartao.AURA);

            criarMetodo("Cartão de Débito - Visa", TipoMetodoPagamento.CARTAO_DEBITO, BandeiraCartao.VISA);
            criarMetodo("Cartão de Débito - Mastercard", TipoMetodoPagamento.CARTAO_DEBITO, BandeiraCartao.MASTERCARD);
            criarMetodo("Cartão de Débito - Elo", TipoMetodoPagamento.CARTAO_DEBITO, BandeiraCartao.ELO);
            criarMetodo("Cartão de Débito - Hipercard", TipoMetodoPagamento.CARTAO_DEBITO, BandeiraCartao.HIPERCARD);
            criarMetodo("Cartão de Débito - Diners Club", TipoMetodoPagamento.CARTAO_DEBITO, BandeiraCartao.DINERS_CLUB);
            criarMetodo("Cartão de Débito - Sorocred", TipoMetodoPagamento.CARTAO_DEBITO, BandeiraCartao.SOROCRED);
            criarMetodo("Cartão de Débito - American Express", TipoMetodoPagamento.CARTAO_DEBITO, BandeiraCartao.AMERICAN_EXPRESS);
            criarMetodo("Cartão de Débito - Aura", TipoMetodoPagamento.CARTAO_DEBITO, BandeiraCartao.AURA);        }
    }
}
