# Sistema de Registro de Ponto em Java 🕒
Este é um projeto simples em Java para controle de ponto de colaboradores. O sistema registra entrada e saída e exibe os registros formatados.

## 📌 Funcionalidades
Registrar horário de entrada

Registrar horário de saída

Imprimir todos ou apenas os últimos registros

Exibir registros com data e hora formatadas

## ⚙️ Integração com Banco de Dados MySQL
Este projeto foi atualizado para trabalhar diretamente com um banco de dados MySQL, buscando e adicionando registros de ponto.

### Dependências
Para a conexão com o MySQL, é utilizado o driver JDBC mysql-connector-j-9.3.0. Certifique-se de adicioná-lo ao seu projeto.

## Script SQL para Criação do Banco de Dados
O seguinte script SQL pode ser utilizado para criar o esquema e a tabela necessários no seu servidor MySQL:
```
CREATE SCHEMA IF NOT EXISTS sistema_ponto;

USE sistema_ponto;

CREATE TABLE IF NOT EXISTS `registros` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `cargo` VARCHAR(100) NOT NULL,
  `tipo` VARCHAR(20) NOT NULL,
  `horario` DATETIME NOT NULL,
  `departamento` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
```