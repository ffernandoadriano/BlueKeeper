CREATE DATABASE bluekeeper;

USE bluekeeper;

CREATE TABLE senha_servico(
  id SMALLINT UNSIGNED NOT NULL,
  login VARCHAR(45) NOT NULL,
  senha VARCHAR(25) NOT NULL,
  servico VARCHAR(45) NOT NULL,
  observacoes VARCHAR(500) NULL,
  PRIMARY KEY(id)
);

SELECT * FROM senha_servico;
