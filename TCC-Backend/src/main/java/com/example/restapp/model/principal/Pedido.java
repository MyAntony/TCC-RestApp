package com.example.restapp.model.principal;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.example.restapp.model.Usuario;
import com.example.restapp.model.produtos.Produto;
import jakarta.persistence.*;
import lombok.*;

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
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false, updatable = false)
    private Usuario usuario;
    
    // @ManyToOne
    // @JoinColumn(name = "mesa_id", nullable = false)
    private Integer mesa; // Está como Integer para testar enquanto a classe Mesa não é criada

    private String descricaoPedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    private Integer quantidadeProduto = 1;

    @Column(nullable = false)
    private Double valorUnitario;

    @Column(nullable = false)
    private Double valorTotal;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private java.time.LocalDateTime horarioLancamento;
}

// CREATE TABLE IF NOT EXISTS `mydb`.`Pedido` (
//   `idPedido` INT NOT NULL AUTO_INCREMENT,
//   `Garcom_idGarcom` INT NOT NULL,
//   `Mesa_idMesa` INT NOT NULL,
//   `horarioLancamento` DATETIME NOT NULL,
//   `descricaoPedido` VARCHAR(45) NULL,
//   `quantidadeProduto` INT NOT NULL,
//   `valorUnitario` DOUBLE NOT NULL,
//   `valorTotal` DOUBLE NOT NULL,
//   PRIMARY KEY (`idPedido`),
//   INDEX `fk_Pedido_Garcom1_idx` (`Garcom_idGarcom` ASC) VISIBLE,
//   INDEX `fk_Pedido_Produto1_idx` (`Produto_idProduto` ASC) VISIBLE,
//   INDEX `fk_Pedido_Mesa1_idx` (`Mesa_idMesa` ASC) VISIBLE,
//   CONSTRAINT `fk_Pedido_Garcom1`
//     FOREIGN KEY (`Garcom_idGarcom`)
//     REFERENCES `mydb`.`Garcom` (`idGarcom`)
//     ON DELETE NO ACTION
//     ON UPDATE NO ACTION,
//   CONSTRAINT `fk_Pedido_Produto1`
//     FOREIGN KEY (`Produto_idProduto`)
//     REFERENCES `mydb`.`Produto` (`idProduto`)
//     ON DELETE NO ACTION
//     ON UPDATE NO ACTION,
//   CONSTRAINT `fk_Pedido_Mesa1`
//     FOREIGN KEY (`Mesa_idMesa`)
//     REFERENCES `mydb`.`Mesa` (`idMesa`)
//     ON DELETE NO ACTION
//     ON UPDATE NO ACTION)
// ENGINE = InnoDB;