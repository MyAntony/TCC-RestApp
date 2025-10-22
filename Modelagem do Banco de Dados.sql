CREATE TABLE IF NOT EXISTS `mydb`.`Garcom` (
  `idGarcom` INT NOT NULL AUTO_INCREMENT,
  `nomeGarcom` VARCHAR(45) NOT NULL,
  `cpfGarcom` VARCHAR(45) NOT NULL,
  `emailGarcom` VARCHAR(45) NOT NULL,
  `numeroGarcom` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idGarcom`),
  UNIQUE INDEX `cpfGarcom_UNIQUE` (`cpfGarcom` ASC) VISIBLE,
  UNIQUE INDEX `emailGarcom_UNIQUE` (`emailGarcom` ASC) VISIBLE,
  UNIQUE INDEX `numeroGarcom_UNIQUE` (`numeroGarcom` ASC) VISIBLE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `mydb`.`CategoriaProdutos` (
  `idCategoriaProdutos` INT NOT NULL AUTO_INCREMENT,
  `nomeCategoriaProdutos` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCategoriaProdutos`),
  UNIQUE INDEX `nomeCategoriaProdutos_UNIQUE` (`nomeCategoriaProdutos` ASC) VISIBLE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `mydb`.`Produto` (
  `idProduto` INT NOT NULL AUTO_INCREMENT,
  `nomeProduto` VARCHAR(45) NOT NULL,
  `CategoriaProdutos_idCategoriaProdutos` INT NOT NULL,
  `precoCusto` DOUBLE NOT NULL,
  `precoVenda` DOUBLE NOT NULL,
  `descricaoProduto` VARCHAR(45) NULL,
  PRIMARY KEY (`idProduto`),
  INDEX `fk_Produto_CategoriaProdutos_idx` (`CategoriaProdutos_idCategoriaProdutos` ASC) VISIBLE,
  UNIQUE INDEX `nomeProduto_UNIQUE` (`nomeProduto` ASC) VISIBLE,
  CONSTRAINT `fk_Produto_CategoriaProdutos`
    FOREIGN KEY (`CategoriaProdutos_idCategoriaProdutos`)
    REFERENCES `mydb`.`CategoriaProdutos` (`idCategoriaProdutos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `mydb`.`Mesa` (
  `idMesa` INT NOT NULL AUTO_INCREMENT,
  `numeroMesa` VARCHAR(45) NOT NULL,
  `horarioAbertura` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `horarioFechamento` DATETIME NOT NULL,
  `garcomResponsavel` INT NULL,
  `descricaoMesa` VARCHAR(45) NULL,
  `statusMesa` ENUM('LIVRE', 'OCUPADA', 'AGUARDANDO_PAGAMENTO') NULL,
  PRIMARY KEY (`idMesa`),
  INDEX `fk_Mesa_Garcom_Responsavel_idx` (`garcomResponsavel` ASC) VISIBLE,
  CONSTRAINT `fk_Mesa_Garcom_Responsavel`
    FOREIGN KEY (`garcomResponsavel`)
    REFERENCES `mydb`.`Garcom` (`idGarcom`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `mydb`.`Pedido` (
  `idPedido` INT NOT NULL AUTO_INCREMENT,
  `Garcom_idGarcom` INT NOT NULL,
  `Produto_idProduto` INT NOT NULL,
  `Mesa_idMesa` INT NOT NULL,
  `horarioLancamento` DATETIME NOT NULL,
  `descricaoPedido` VARCHAR(45) NULL,
  `quantidadeProduto` INT NOT NULL,
  `valorUnitario` DOUBLE NOT NULL,
  `valorTotal` DOUBLE NOT NULL,
  PRIMARY KEY (`idPedido`),
  INDEX `fk_Pedido_Garcom1_idx` (`Garcom_idGarcom` ASC) VISIBLE,
  INDEX `fk_Pedido_Produto1_idx` (`Produto_idProduto` ASC) VISIBLE,
  INDEX `fk_Pedido_Mesa1_idx` (`Mesa_idMesa` ASC) VISIBLE,
  CONSTRAINT `fk_Pedido_Garcom1`
    FOREIGN KEY (`Garcom_idGarcom`)
    REFERENCES `mydb`.`Garcom` (`idGarcom`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_Produto1`
    FOREIGN KEY (`Produto_idProduto`)
    REFERENCES `mydb`.`Produto` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_Mesa1`
    FOREIGN KEY (`Mesa_idMesa`)
    REFERENCES `mydb`.`Mesa` (`idMesa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;