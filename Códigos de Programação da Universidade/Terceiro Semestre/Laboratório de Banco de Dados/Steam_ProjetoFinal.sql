/* PROJETO FINAL - LABORATÓRIO DE BANCO DE DADOS
Instruções e Estrutura do Arquivo .sql

Identificação do Grupo: 
Inserir os nomes completos e números de matrícula dos integrantes do grupo

-
Alterações na Modelagem 
Indique se houve alterações na modelagem de dados:
( X ) Sim. O arquivo atualizado foi enviado novamente no AVA. 
( ) Não.

ORIENTAÇÕES GERAIS
- As respostas devem ser inseridas diretamente no local indicado pelos enunciados. 
- Arquivos desorganizados não serão corrigidos.
- Consultas SQL muito simples não serão aceitas. Evitem respostas com apenas SELECT, FROM e ORDER BY. 
- Elaborem as queries com atenção aos critérios exigidos.
- A presença na apresentação é obrigatória para a atribuição de nota. Alunos ausentes receberão nota zero no trabalho.

O trabalho deve ser entregue em um único arquivo no formato .sql, contendo todos os itens descritos a seguir:
*/

-- 1. Criação do Banco de Dados e Estrutura
-- Criação do banco de dados.

CREATE DATABASE IF NOT EXISTS steam;
USE steam;

-- Criação das tabelas conforme a modelagem apresentada.

-- Tabela: pix
CREATE TABLE IF NOT EXISTS pix (
  id INT AUTO_INCREMENT PRIMARY KEY,
  chave_pix VARCHAR(320) NOT NULL UNIQUE,
  id_transacao_pix VARCHAR(255) UNIQUE
);

-- Tabela: boleto
CREATE TABLE IF NOT EXISTS boleto (
  id INT AUTO_INCREMENT PRIMARY KEY,
  numero_boleto VARCHAR(48) NOT NULL,
  data_vencimento DATE NOT NULL,
  local_digitavel VARCHAR(54) NOT NULL UNIQUE,
  url_boleto VARCHAR(500)
);

-- Tabela: conquistas
CREATE TABLE IF NOT EXISTS conquistas (
  idConquistas INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(45) NOT NULL,
  tipos_conquistas ENUM('Exploração', 'Combate', 'História', 'Comedia', 'Acao', 'Terror')
);

-- Tabela: jogo
CREATE TABLE IF NOT EXISTS jogo (
  idJogo INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(45) NOT NULL,
  descricao TEXT NOT NULL,
  preco DECIMAL(6,2) NOT NULL,
  conquistas_idConquistas INT NOT NULL,
  data_atualizacao DATETIME DEFAULT CURRENT_TIMESTAMP,
  requisitos_minimos TEXT,
  requisitos_recomendados TEXT,
  deletado TINYINT DEFAULT 0,
  data_delecao DATETIME,
  INDEX idx_jogo_deletado (deletado),
  FOREIGN KEY (conquistas_idConquistas) REFERENCES conquistas(idConquistas)
);

-- Tabela: categoria
CREATE TABLE IF NOT EXISTS categoria (
  idCategoria INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(45) NOT NULL,
  descricao TEXT NOT NULL
);

-- Tabela: plataforma
CREATE TABLE IF NOT EXISTS plataforma (
  idPlataforma INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(30) NOT NULL,
  descricao TEXT NOT NULL
);

-- Tabela: usuarios
CREATE TABLE IF NOT EXISTS usuarios (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  cpf CHAR(11) NOT NULL UNIQUE,
  apelido VARCHAR(45) NOT NULL UNIQUE,
  senha VARCHAR(255) NOT NULL,
  status ENUM('Ativo', 'Desativado', 'Deletado', 'Suspenso') NOT NULL,
  saldo DECIMAL(12,2),
  data_atualizacao DATETIME DEFAULT CURRENT_TIMESTAMP,
  data_nascimento DATETIME,
  deletado TINYINT DEFAULT 0,
  data_deletado DATETIME,
  INDEX idx_usuarios_deletado (deletado)
);

-- Tabela: jogo_adquirido
CREATE TABLE IF NOT EXISTS jogo_adquirido (
  id INT AUTO_INCREMENT PRIMARY KEY,
  data_aquisicao DATETIME NOT NULL,
  horas_jogadas TIME,
  usuarios_idUsuarios INT NOT NULL,
  jogo_idJogo INT NOT NULL,
  deletado TINYINT DEFAULT 0,
  data_deletado DATETIME,
  INDEX idx_jogo_adquirido_deletado (deletado),
  FOREIGN KEY (usuarios_idUsuarios) REFERENCES usuarios(id),
  FOREIGN KEY (jogo_idJogo) REFERENCES jogo(idJogo)
);

-- Tabela: pagamentos
CREATE TABLE IF NOT EXISTS pagamentos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  valor DECIMAL(6,2) NOT NULL,
  data_pagamento DATETIME NOT NULL,
  status ENUM('Pago', 'Pendente', 'Não Pago', 'Cancelado') NOT NULL,
  metodo_pagamento ENUM('Cartao', 'Pix', 'Boleto', 'Saldo'),
  id_cartao INT,
  id_pix INT,
  id_boleto INT,
  id_jogo_adquirido INT,
  id_usuario INT NOT NULL,
  FOREIGN KEY (id_pix) REFERENCES pix(id),
  FOREIGN KEY (id_boleto) REFERENCES boleto(id),
  FOREIGN KEY (id_jogo_adquirido) REFERENCES jogo_adquirido(id),
  FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

-- Tabela: cartao
CREATE TABLE IF NOT EXISTS cartao (
  id INT AUTO_INCREMENT PRIMARY KEY,
  numero VARCHAR(16) NOT NULL UNIQUE,
  validade DATE NOT NULL,
  cvv CHAR(3) NOT NULL,
  tipo ENUM('Credito', 'Debito') NOT NULL,
  bandeira ENUM(
    'Visa', 'MasterCard', 'American Express', 'Elo',
    'Hipercard', 'Aura', 'Diners Club', 'Discover', 'JCB'
  ) NOT NULL,
  usuarios_idUsuarios INT NOT NULL,
  ultimos_digitos CHAR(4) NOT NULL,
  nome_titular VARCHAR(100) NOT NULL,
  apelido_cartao VARCHAR(50),
  data_adicao DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (usuarios_idUsuarios) REFERENCES usuarios(id)
);

-- Tabela: conquistas_adquiridas
CREATE TABLE IF NOT EXISTS conquistas_adquiridas (
  idConquistasAdquiridas INT AUTO_INCREMENT PRIMARY KEY,
  data_adquirida DATETIME NOT NULL,
  jogoAdquirido_idJogoAdquirido INT NOT NULL,
  FOREIGN KEY (jogoAdquirido_idJogoAdquirido) REFERENCES jogo_adquirido(id)
);

-- Tabela: suporte
CREATE TABLE IF NOT EXISTS suporte (
  idSuporte INT AUTO_INCREMENT PRIMARY KEY,
  data DATETIME NOT NULL,
  descricao TEXT NOT NULL,
  usuarios_idUsuarios INT NOT NULL,
  FOREIGN KEY (usuarios_idUsuarios) REFERENCES usuarios(id)
);

-- Tabela: avaliacoes
CREATE TABLE IF NOT EXISTS avaliacoes (
  idAvaliacoes INT AUTO_INCREMENT,
  nota DECIMAL(2,1) NOT NULL,
  comentario TEXT,
  jogo_idJogo INT NOT NULL,
  usuarios_idUsuarios INT NOT NULL,
  data_atualizacao DATETIME DEFAULT CURRENT_TIMESTAMP,
  deletado TINYINT DEFAULT 0,
  data_selecao DATETIME,
  PRIMARY KEY (idAvaliacoes, jogo_idJogo),
  INDEX idx_avaliacoes_deletado (deletado),
  FOREIGN KEY (jogo_idJogo) REFERENCES jogo(idJogo),
  FOREIGN KEY (usuarios_idUsuarios) REFERENCES usuarios(id)
);

-- Tabela: categoria_has_jogo
CREATE TABLE IF NOT EXISTS categoria_has_jogo (
  categoria_idCategoria INT NOT NULL,
  jogo_idJogo INT NOT NULL,
  PRIMARY KEY (categoria_idCategoria, jogo_idJogo),
  FOREIGN KEY (categoria_idCategoria) REFERENCES categoria(idCategoria),
  FOREIGN KEY (jogo_idJogo) REFERENCES jogo(idJogo)
);

-- Tabela: plataforma_has_jogo
CREATE TABLE IF NOT EXISTS plataforma_has_jogo (
  plataforma_idPlataforma INT NOT NULL,
  jogo_idJogo INT NOT NULL,
  PRIMARY KEY (plataforma_idPlataforma, jogo_idJogo),
  FOREIGN KEY (plataforma_idPlataforma) REFERENCES plataforma(idPlataforma),
  FOREIGN KEY (jogo_idJogo) REFERENCES jogo(idJogo)
);

-- Tabela: amizade
CREATE TABLE IF NOT EXISTS amizade (
  idAmizade INT AUTO_INCREMENT PRIMARY KEY,
  usuarios_idUsuariosMandou INT NOT NULL,
  usuarios_idUsuariosRecebeu INT NOT NULL,
  situacao ENUM('Amigos', 'Pendente', 'Bloqueado', 'Recusado') NOT NULL,
  FOREIGN KEY (usuarios_idUsuariosMandou) REFERENCES usuarios(id),
  FOREIGN KEY (usuarios_idUsuariosRecebeu) REFERENCES usuarios(id)
);


-- 2. Inserção de Dados
-- Inserção de dados em todas as tabelas. Cada tabela deve conter no mínimo 30 comandos INSERT. É permitido utilizar ferramentas auxiliares para gerar dados, como o ChatGPT ou geradores automáticos de dados fictícios.

-- dados para a tabela usuarios
INSERT INTO usuarios (nome, cpf, apelido, senha, status, saldo, data_nascimento)
VALUES 
('Ana Clara Souza', '12345678900', 'aninha', 'senha123', 'Ativo', 150.50, '1998-05-12'),
('Bruno Oliveira', '23456789100', 'brunex', 'abc123', 'Ativo', 520.90, '1995-03-10'),
('Carlos Silva', '34567891200', 'kaká', 'abc123', 'Desativado', 85.00, '1992-07-30'),
('Daniela Rocha', '45678912300', 'dani_r', 'abc123', 'Ativo', 0.00, '2000-10-21'),
('Eduardo Lima', '56789123400', 'dudu', 'abc123', 'Suspenso', 1.20, '1999-09-09'),
('Fernanda Azevedo', '67891234500', 'feh', 'abc123', 'Deletado', 0.00, '1988-04-15'),
('Gabriel Mendes', '78912345600', 'gmendes', 'abc123', 'Ativo', 78.90, '1993-01-01'),
('Helena Martins', '89123456700', 'lena', 'abc123', 'Ativo', 320.00, '2002-11-11'),
('Igor Santana', '91234567800', 'igorsan', 'abc123', 'Suspenso', 50.50, '1997-12-25'),
('Joana Prado', '98765432100', 'jojo', 'abc123', 'Ativo', 230.75, '1990-06-18'),
('Lucas Pereira', '10123456789', 'lukinhas', 'senha321', 'Ativo', 105.00, '1994-08-04'),
('Mariana Castro', '10234567890', 'maryc', '123senha', 'Ativo', 205.00, '1998-12-14'),
('Nelson Ribeiro', '10345678901', 'neltio', 'senha000', 'Desativado', 0.00, '1989-03-20'),
('Olívia Braga', '10456789012', 'liv', 'braga2023', 'Suspenso', 55.55, '2001-05-28'),
('Pedro Fonseca', '10567890123', 'pedrão', 'fon123', 'Ativo', 888.88, '1996-07-07'),
('Quésia Moraes', '10678901234', 'quesi', 'banana89', 'Ativo', 30.00, '2003-02-03'),
('Rafael Gomes', '10789012345', 'rafinha', 'rafa2024', 'Ativo', 12.30, '1995-09-09'),
('Sabrina Teixeira', '10890123456', 'sah_tx', '987654', 'Deletado', 0.00, '1991-03-03'),
('Tiago Monteiro', '10901234567', 'tiagom', 'ti@go', 'Ativo', 60.00, '1992-06-17'),
('Úrsula Dias', '11012345678', 'ursinha', 'urs123', 'Ativo', 310.30, '2000-12-01'),
('Vinícius Nobre', '11123456789', 'vinnn', 'vin123', 'Ativo', 555.55, '1987-10-10'),
('Wesley Alves', '11234567890', 'weslley', 'pass1234', 'Suspenso', 5.50, '2002-08-08'),
('Xuxa Lima', '11345678901', 'xuxu', 'lima567', 'Ativo', 0.00, '1975-11-11'),
('Yasmin Ventura', '11456789012', 'yas', 'abc456', 'Desativado', 80.80, '1999-04-22'),
('Zeca Loureiro', '11567890123', 'zecão', 'zeca123', 'Ativo', 70.00, '1993-05-05'),
('Arthur Borges', '11678901234', 'arth', 'borg23', 'Ativo', 40.00, '1996-09-14'),
('Beatriz Matos', '11789012345', 'bia', 'b3a123', 'Ativo', 200.00, '1990-02-02'),
('Caio Dantas', '11890123456', 'caiozinho', '321abc', 'Ativo', 390.20, '1988-06-06'),
('Diana Costa', '11901234567', 'didi', 'dia2023', 'Suspenso', 42.42, '2001-01-01'),
('Elias Fernandes', '12012345678', 'elias', 'ef1234', 'Ativo', 1000.00, '1994-03-09');

-- Valores para a tabela pix
INSERT INTO pix (chave_pix, id_transacao_pix) VALUES
('rafael@email.com', 'TX1234567890'),
('12345678900', 'TX1234567891'),
('telefone1@pix.com', 'TX1234567892'),
('pix_key_004@bank.com', 'TX1234567893'),
('carol@pix.com', 'TX1234567894'),
('pix_user_006@bank.com', 'TX1234567895'),
('marcos123@pix.com', 'TX1234567896'),
('contato.fernanda@pix.com', 'TX1234567897'),
('11122233344', 'TX1234567898'),
('lucas_phone@pix.com', 'TX1234567899'),
('yasmim.pix@bank.com', 'TX1234567900'),
('jorge_789@pix.com', 'TX1234567901'),
('celia_costa@pix.com', 'TX1234567902'),
('pix123456@fastbank.com', 'TX1234567903'),
('mateus_key@pix.com', 'TX1234567904'),
('aline.silva@pix.com', 'TX1234567905'),
('joaozinho@pixmail.com', 'TX1234567906'),
('clara_m@pix.com', 'TX1234567907'),
('andre.lins@pix.com', 'TX1234567908'),
('danilo_fernandes@pix.com', 'TX1234567909'),
('vanessa.l@pix.com', 'TX1234567910'),
('michele.pix@bank.com', 'TX1234567911'),
('caio_monteiro@pix.com', 'TX1234567912'),
('robson@pix.com', 'TX1234567913'),
('giovanna.email@pix.com', 'TX1234567914'),
('paulaborges@pix.com', 'TX1234567915'),
('pix_teste26@server.com', 'TX1234567916'),
('usuario27@pix.com', 'TX1234567917'),
('thiago_silva@pix.com', 'TX1234567918'),
('pix_key_rafa@pix.com', 'TX1234567919');

-- Valores para a tabela boleto
INSERT INTO boleto (numero_boleto, data_vencimento, local_digitavel, url_boleto) VALUES
('341917600812345678901234567890123456789012345678', '2025-07-10', '34191.76008 12345.678901 23456.789012 3 67890123456789', 'https://boletos.com/b/1'),
('341917600812345678901234567890123456789012345679', '2025-07-11', '34191.76008 12345.678901 23456.789013 4 67890123456790', 'https://boletos.com/b/2'),
('104997620123456789012345678901234567890123456789', '2025-07-12', '10499.76201 23456.789012 34567.890123 1 67890123456789', 'https://boletos.com/b/3'),
('033991230123456789012345678901234567890123456789', '2025-07-13', '03399.12301 23456.789012 34567.890123 2 67890123456789', 'https://boletos.com/b/4'),
('237938120123456789012345678901234567890123456789', '2025-07-14', '23793.81201 23456.789012 34567.890123 3 67890123456789', 'https://boletos.com/b/5'),
('104917600812345678901234567890123456789012345678', '2025-07-15', '10491.76008 12345.678901 23456.789012 4 67890123456789', 'https://boletos.com/b/6'),
('001900000912345678901234567890123456789012345678', '2025-07-16', '00190.00009 12345.678901 23456.789012 1 67890123456789', 'https://boletos.com/b/7'),
('001900000912345678901234567890123456789012345679', '2025-07-17', '00190.00009 12345.678901 23456.789013 2 67890123456790', 'https://boletos.com/b/8'),
('104900000912345678901234567890123456789012345678', '2025-07-18', '10490.00009 12345.678901 23456.789012 3 67890123456789', 'https://boletos.com/b/9'),
('033900000912345678901234567890123456789012345678', '2025-07-19', '03390.00009 12345.678901 23456.789012 4 67890123456789', 'https://boletos.com/b/10'),
('237947120123456789012345678901234567890123456789', '2025-07-20', '23794.71201 23456.789012 34567.890123 5 67890123456789', 'https://boletos.com/b/11'),
('104911110912345678901234567890123456789012345678', '2025-07-21', '10491.11109 12345.678901 23456.789012 6 67890123456789', 'https://boletos.com/b/12'),
('237900000912345678901234567890123456789012345678', '2025-07-22', '23790.00009 12345.678901 23456.789012 7 67890123456789', 'https://boletos.com/b/13'),
('104944440912345678901234567890123456789012345678', '2025-07-23', '10494.44409 12345.678901 23456.789012 8 67890123456789', 'https://boletos.com/b/14'),
('341944440912345678901234567890123456789012345678', '2025-07-24', '34194.44409 12345.678901 23456.789012 9 67890123456789', 'https://boletos.com/b/15'),
('237955550912345678901234567890123456789012345678', '2025-07-25', '23795.55509 12345.678901 23456.789012 0 67890123456789', 'https://boletos.com/b/16'),
('033966660912345678901234567890123456789012345678', '2025-07-26', '03396.66609 12345.678901 23456.789012 1 67890123456789', 'https://boletos.com/b/17'),
('104977770912345678901234567890123456789012345678', '2025-07-27', '10497.77709 12345.678901 23456.789012 2 67890123456789', 'https://boletos.com/b/18'),
('104988880912345678901234567890123456789012345678', '2025-07-28', '10498.88809 12345.678901 23456.789012 3 67890123456789', 'https://boletos.com/b/19'),
('104999990912345678901234567890123456789012345678', '2025-07-29', '10499.99909 12345.678901 23456.789012 4 67890123456789', 'https://boletos.com/b/20'),
('341900000012345678901234567890123456789012345678', '2025-07-30', '34190.00000 12345.678901 23456.789012 5 67890123456789', 'https://boletos.com/b/21'),
('001988880912345678901234567890123456789012345678', '2025-07-31', '00198.88809 12345.678901 23456.789012 6 67890123456789', 'https://boletos.com/b/22'),
('237911110912345678901234567890123456789012345678', '2025-08-01', '23791.11109 12345.678901 23456.789012 7 67890123456789', 'https://boletos.com/b/23'),
('104922220912345678901234567890123456789012345678', '2025-08-02', '10492.22209 12345.678901 23456.789012 8 67890123456789', 'https://boletos.com/b/24'),
('033933330912345678901234567890123456789012345678', '2025-08-03', '03393.33309 12345.678901 23456.789012 9 67890123456789', 'https://boletos.com/b/25'),
('033944440912345678901234567890123456789012345678', '2025-08-04', '03394.44409 12345.678901 23456.789012 0 67890123456789', 'https://boletos.com/b/26'),
('237922220912345678901234567890123456789012345678', '2025-08-05', '23792.22209 12345.678901 23456.789012 1 67890123456789', 'https://boletos.com/b/27'),
('104933330912345678901234567890123456789012345678', '2025-08-06', '10493.33309 12345.678901 23456.789012 2 67890123456789', 'https://boletos.com/b/28'),
('341922220912345678901234567890123456789012345678', '2025-08-07', '34192.22209 12345.678901 23456.789012 3 67890123456789', 'https://boletos.com/b/29'),
('341933330912345678901234567890123456789012345678', '2025-08-08', '34193.33309 12345.678901 23456.789012 4 67890123456789', 'https://boletos.com/b/30');

-- Valores para a tabela conquistas
INSERT INTO conquistas (nome, tipos_conquistas) VALUES
('Mestre da Exploração', 'Exploração'),
('Caçador de Relíquias', 'Exploração'),
('Viajante Inabalável', 'Exploração'),
('Caminhante das Sombras', 'Exploração'),
('Descobridor de Ruínas', 'Exploração'),
('Veterano de Batalhas', 'Combate'),
('Lâmina Imbatível', 'Combate'),
('Executor Fantasma', 'Combate'),
('Domador de Chefões', 'Combate'),
('Matador de Mil', 'Combate'),
('Herói da Resistência', 'História'),
('Guardião do Reino', 'História'),
('Lenda Viva', 'História'),
('Eco das Eras', 'História'),
('Narrador de Destinos', 'História'),
('Piada Mortal', 'Comedia'),
('Engraçadinho Oficial', 'Comedia'),
('Comediante de Plantão', 'Comedia'),
('Zoador Supremo', 'Comedia'),
('Rei das Tretas', 'Comedia'),
('Imparável', 'Acao'),
('Velocidade Máxima', 'Acao'),
('Ninja Urbano', 'Acao'),
('Acrobata Letal', 'Acao'),
('Assaltante Veloz', 'Acao'),
('Pesadelo Vivo', 'Terror'),
('Caçador de Horrores', 'Terror'),
('Sobrevivente Solitário', 'Terror'),
('Alma Condenada', 'Terror'),
('Último Suspiro', 'Terror');

-- Valores para a tabela jogo
INSERT INTO jogo (nome, descricao, preco, conquistas_idConquistas, requisitos_minimos, requisitos_recomendados) VALUES
('Shadow Realms', 'Aventura sombria em reinos esquecidos.', 59.90, 1, 'CPU i3, 4GB RAM, GPU integrada', 'CPU i5, 8GB RAM, GTX 960'),
('CyberRunner', 'Corridas em uma metrópole cibernética.', 39.99, 2, 'i5, 6GB RAM, GPU 2GB', 'i7, 16GB RAM, RTX 2060'),
('Terror Noturno', 'Sobrevivência em uma cidade assombrada.', 49.00, 3, 'Dual-Core, 4GB RAM, Intel HD', 'i5, 8GB RAM, GTX 1050'),
('Piadas Cósmicas', 'Comédia intergaláctica com aliens.', 29.00, 4, 'i3, 2GB RAM', 'i5, 4GB RAM'),
('Lendas do Reino', 'História épica de cavaleiros e dragões.', 69.00, 5, 'i5, 8GB RAM, GTX 750', 'i7, 16GB RAM, GTX 1070'),
('Ultimate Combat X', 'Lutas frenéticas com poderes.', 59.90, 6, 'i5, 4GB RAM, GPU dedicada', 'i7, 8GB RAM, GTX 970'),
('Parkour City', 'Ação veloz em cenários urbanos.', 19.90, 7, 'CPU Dual Core, 2GB RAM', 'i5, 4GB RAM, GTX 660'),
('Rindo até o Fim', 'Jogo de comédia com minigames.', 25.00, 8, 'i3, 4GB RAM', 'i5, 6GB RAM'),
('Segredos do Submundo', 'Exploração em cavernas profundas.', 45.00, 9, 'i5, 6GB RAM, GPU 1GB', 'i7, 16GB RAM, GTX 1650'),
('Zona Zero', 'Combate militar futurista.', 65.00, 10, 'i5, 4GB RAM, GTX 1050', 'i7, 8GB RAM, RTX 3050'),
('Reflexos Imediatos', 'Plataforma com desafios de tempo.', 15.90, 11, 'i3, 2GB RAM', 'i5, 4GB RAM'),
('O Sétimo Portal', 'Exploração dimensional e enigmas.', 33.30, 12, 'Dual-Core, 3GB RAM', 'i5, 6GB RAM'),
('Cavaleiros do Amanhã', 'RPG tático com estratégia e combate.', 42.00, 13, 'i5, 6GB RAM', 'i7, 12GB RAM'),
('Gritos da Neblina', 'Terror psicológico em mansões.', 48.00, 14, 'i3, 4GB RAM', 'i5, 8GB RAM, GTX 760'),
('Piadas de Outro Mundo', 'Comédia nonsense e extraterrestres.', 20.00, 15, 'i3, 2GB RAM', 'i5, 4GB RAM'),
('Espíritos da Vingança', 'Terror de sobrevivência.', 56.60, 16, 'i5, 8GB RAM, GPU 2GB', 'i7, 16GB RAM, RTX 2060'),
('Operação Fogo Cruzado', 'FPS com missões intensas.', 69.90, 17, 'i5, 8GB RAM', 'i7, 12GB RAM, GTX 1060'),
('Mistérios do Deserto', 'Ação e descoberta em ruínas antigas.', 38.50, 18, 'i3, 3GB RAM', 'i5, 6GB RAM'),
('Reino da Gargalhada', 'Miniaventuras engraçadas.', 19.90, 19, 'i3, 2GB RAM', 'i5, 4GB RAM'),
('Fragmentos do Tempo', 'Narrativa sobre viagens temporais.', 49.50, 20, 'i5, 6GB RAM', 'i7, 8GB RAM, GTX 960'),
('Mega Drift', 'Corridas com física realista.', 45.00, 21, 'i5, 4GB RAM, GPU integrada', 'i7, 8GB RAM, GTX 1650'),
('Cemitério Profundo', 'Terror em primeira pessoa.', 54.99, 22, 'i5, 6GB RAM', 'i7, 12GB RAM'),
('Comédia Fatal', 'Jogo sarcástico com desafios hilários.', 27.30, 23, 'i3, 2GB RAM', 'i5, 4GB RAM'),
('O Último Risada', 'Stand-up virtual com minigames.', 21.00, 24, 'Dual-Core, 3GB RAM', 'i5, 6GB RAM'),
('Domínio Sombrio', 'Ação e magia em mundo destruído.', 61.00, 25, 'i5, 8GB RAM', 'i7, 16GB RAM, GTX 1080'),
('Fúria de Marte', 'Combate espacial em Marte.', 67.00, 26, 'i5, 6GB RAM', 'i7, 8GB RAM, RTX 2060'),
('Parque do Riso', 'Comédia em parque temático.', 22.22, 27, 'i3, 4GB RAM', 'i5, 6GB RAM'),
('Retorno dos Caçadores', 'Ação cooperativa com monstros.', 58.00, 28, 'i5, 8GB RAM', 'i7, 16GB RAM'),
('Armadilhas e Tretas', 'Comédia e ação em 2D.', 18.90, 29, 'i3, 2GB RAM', 'i5, 4GB RAM'),
('Sobrevivência Glacial', 'Terror e estratégia em planeta congelado.', 53.30, 30, 'i5, 6GB RAM', 'i7, 8GB RAM, GTX 1070');

-- Valores para a tabela categoria
INSERT INTO categoria (nome, descricao) VALUES
('Ação', 'Jogos com foco em combates, reflexos rápidos e desafios intensos.'),
('Aventura', 'Jogos com exploração e narrativa como foco principal.'),
('Terror', 'Jogos que buscam causar medo e tensão.'),
('Comédia', 'Jogos com humor, sátiras e situações engraçadas.'),
('RPG', 'Jogos de interpretação de personagem com progressão e escolhas.'),
('Esportes', 'Jogos com simulação de esportes reais ou fictícios.'),
('Corrida', 'Competição sobre rodas em alta velocidade.'),
('Simulação', 'Jogos que imitam experiências ou sistemas da vida real.'),
('Estratégia', 'Jogos que exigem raciocínio tático e planejamento.'),
('Casual', 'Jogos leves e rápidos para passar o tempo.'),
('Puzzle', 'Jogos focados em lógica e solução de problemas.'),
('Educacional', 'Jogos com propósito pedagógico.'),
('Sandbox', 'Jogos abertos com liberdade total ao jogador.'),
('Plataforma', 'Jogos com foco em saltos e obstáculos.'),
('Stealth', 'Jogos com foco em furtividade e disfarce.'),
('MMO', 'Massive Multiplayer Online — muitos jogadores interagindo.'),
('FPS', 'First Person Shooter — jogos de tiro em primeira pessoa.'),
('TPS', 'Third Person Shooter — tiro em terceira pessoa.'),
('Narrativo', 'Histórias interativas com múltiplas escolhas.'),
('Hack and Slash', 'Combates frenéticos com espadas e armas brancas.'),
('Sobrevivência', 'Jogos em que o jogador precisa sobreviver em ambientes hostis.'),
('Horror Psicológico', 'Terror baseado em tensão mental e atmosfera.'),
('Sci-Fi', 'Jogos ambientados em ficção científica.'),
('Fantasia', 'Mundos mágicos com criaturas e poderes.'),
('Musical', 'Jogos rítmicos e interativos com músicas.'),
('Indie', 'Jogos independentes com mecânicas criativas.'),
('Cooperativo', 'Jogos com foco em jogar em equipe.'),
('Competitivo', 'Jogos centrados em desafios entre jogadores.'),
('Realidade Virtual', 'Jogos desenvolvidos para VR.'),
('Retro', 'Jogos com estética e mecânica de gerações antigas.');

-- Valores para a tabela plataforma
INSERT INTO plataforma (nome, descricao) VALUES
('Steam', 'Plataforma de distribuição digital de jogos para PC e Mac.'),
('Epic Games Store', 'Loja de jogos digitais com títulos exclusivos.'),
('PlayStation 4', 'Console de oitava geração da Sony.'),
('PlayStation 5', 'Console de nova geração da Sony com SSD ultrarrápido.'),
('Xbox One', 'Console da Microsoft com foco em entretenimento.'),
('Xbox Series X', 'Console de nova geração com desempenho elevado.'),
('Nintendo Switch', 'Console híbrido da Nintendo que combina portátil e TV.'),
('PC (Windows)', 'Jogos compatíveis com o sistema operacional Windows.'),
('PC (Linux)', 'Jogos compatíveis com distribuições Linux.'),
('PC (Mac)', 'Jogos desenvolvidos para macOS.'),
('Android', 'Jogos para smartphones e tablets Android.'),
('iOS', 'Jogos para dispositivos Apple como iPhone e iPad.'),
('Web Browser', 'Jogos que rodam direto no navegador, sem instalação.'),
('Google Stadia', 'Plataforma de jogos via streaming da Google.'),
('GeForce NOW', 'Serviço de jogos por streaming da NVIDIA.'),
('Amazon Luna', 'Plataforma de games da Amazon via nuvem.'),
('Meta Quest', 'Plataforma de realidade virtual da Meta.'),
('Itch.io', 'Loja voltada para jogos independentes.'),
('Battle.net', 'Distribuição digital de jogos da Blizzard.'),
('Ubisoft Connect', 'Plataforma oficial de jogos da Ubisoft.'),
('Origin', 'Distribuidora de jogos da Electronic Arts.'),
('Rockstar Launcher', 'Plataforma de jogos da Rockstar Games.'),
('PS Vita', 'Console portátil da Sony.'),
('Nintendo 3DS', 'Console portátil da Nintendo com gráficos em 3D.'),
('Xbox Cloud Gaming', 'Jogos via nuvem disponíveis no Xbox Game Pass.'),
('DOSBox', 'Emulador para jogos antigos de MS-DOS.'),
('Apple Arcade', 'Serviço de assinatura de jogos da Apple.'),
('GOG Galaxy', 'Plataforma DRM-free com foco em jogos clássicos.'),
('VRChat', 'Mundo virtual interativo com foco em multiplayer.'),
('Emulador Android', 'Execução de jogos Android em computadores.');

-- Valores para a tabela cartao
INSERT INTO cartao (numero, validade, cvv, tipo, bandeira, usuarios_idUsuarios, ultimos_digitos, nome_titular, apelido_cartao, data_adicao) VALUES
('4111111111111111', '2027-05-01', '123', 'Credito', 'Visa', 1, '1111', 'Rafael Silva', 'Principal', NOW()),
('5555555555554444', '2026-09-01', '456', 'Debito', 'MasterCard', 2, '4444', 'Ana Souza', 'Cartão Ana', NOW()),
('378282246310005',  '2028-12-01', '789', 'Credito', 'American Express', 3, '0005', 'Carlos Lima', 'Amex Top', NOW()),
('6011111111111117', '2026-08-01', '321', 'Credito', 'Discover', 4, '1117', 'Fernanda Alves', 'Desc Club', NOW()),
('3530111333300000', '2027-02-01', '987', 'Debito', 'JCB', 5, '0000', 'Igor Moraes', 'Japonês', NOW()),
('30569309025904',   '2027-11-01', '741', 'Credito', 'Diners Club', 6, '5904', 'Joana Prado', 'Diners', NOW()),
('36259600000004',   '2029-07-01', '852', 'Credito', 'Aura', 7, '0004', 'Lucas Borges', 'Aura Prime', NOW()),
('6042030000000000', '2025-10-01', '963', 'Debito', 'Hipercard', 8, '0000', 'Mariana Rios', 'Hipercard', NOW()),
('4012000033330026','2026-06-01', '147', 'Credito', 'Visa', 9, '0026', 'Nicolas Teixeira', 'Cartão 1', NOW()),
('4000000000000002','2028-01-01', '258', 'Debito', 'Elo', 10, '0002', 'Olívia Fernandes', 'Elo Débito', NOW()),
('4111111111111234', '2025-12-31', '159', 'Credito', 'Visa', 11, '1234', 'Paulo Dantas', 'Reserva', NOW()),
('5555222233334444', '2027-04-30', '753', 'Credito', 'MasterCard', 12, '4444', 'Quésia Monteiro', 'Cartão Q', NOW()),
('6011556448578945','2026-08-01', '258', 'Credito', 'Discover', 13, '9458', 'Rafael Duarte', 'Desc 2', NOW()),
('378734493671000',  '2029-09-01', '456', 'Credito', 'American Express', 14, '1000', 'Sabrina Gomes', 'Amex', NOW()),
('6011000990139424', '2027-03-15', '369', 'Debito', 'Hipercard', 15, '9424', 'Tiago Monteiro', 'Hip Débito', NOW()),
('3566002020360505', '2026-07-01', '147', 'Credito', 'JCB', 16, '0505', 'Ursula Dias', 'Japão Card', NOW()),
('3059999999999999', '2028-12-01', '951', 'Credito', 'Diners Club', 17, '9999', 'Vinícius Nobre', 'Elite DC', NOW()),
('5066999999999999', '2025-11-01', '753', 'Debito', 'Elo', 18, '9999', 'Wesley Rodrigues', 'Débito Elo', NOW()),
('6011000400000000','2027-06-01', '951', 'Credito', 'Discover', 19, '0000', 'Xuxa Alves', 'Desc 3', NOW()),
('4000000000000119','2025-07-01', '123', 'Debito', 'Visa', 20, '0119', 'Yasmin Rosa', 'Cartão Yas', NOW()),
('4111111111112222','2029-05-01', '753', 'Credito', 'Visa', 21, '2222', 'Zeca Loureiro', 'Visa Final 22', NOW()),
('5555666677778888','2026-03-01', '159', 'Debito', 'MasterCard', 22, '8888', 'Arthur Borges', 'MC Débito', NOW()),
('4012888888881881','2027-09-01', '357', 'Credito', 'Visa', 23, '1881', 'Beatriz Matos', 'Visa 81', NOW()),
('4111111111119999','2025-10-01', '159', 'Credito', 'Visa', 24, '9999', 'Caio Dantas', 'Principal', NOW()),
('4000000000005678','2027-11-01', '258', 'Credito', 'Elo', 25, '5678', 'Diana Costa', 'Elo Gold', NOW()),
('5105105105105100','2026-04-01', '369', 'Credito', 'MasterCard', 26, '5100', 'Elias Fernandes', 'MC Pro', NOW()),
('371449635398431', '2028-01-01', '852', 'Credito', 'American Express', 27, '8431', 'Felipe Cunha', 'Amex Extra', NOW()),
('6011000995500000','2029-02-01', '753', 'Debito', 'Hipercard', 28, '0000', 'Gabriela Santos', 'Hip Só', NOW()),
('3056930000000004','2025-08-01', '456', 'Credito', 'Diners Club', 29, '0004', 'Helena Matos', 'Diners Night', NOW()),
('3530111111111111','2026-10-01', '147', 'Debito', 'JCB', 30, '1111', 'Iago Ferreira', 'JCB Final 11', NOW());

-- Valores para a tabela jogo_adquirido
INSERT INTO jogo_adquirido (data_aquisicao, horas_jogadas, usuarios_idUsuarios, jogo_idJogo) VALUES
('2025-07-01 10:00:00', '12:30:00', 1, 1),
('2025-07-02 15:00:00', '08:00:00', 2, 2),
('2025-07-03 09:30:00', '04:42:00', 3, 3),
('2025-07-04 14:20:00', '11:00:00', 4, 4),
('2025-07-05 13:00:00', '06:18:00', 5, 5),
('2025-07-06 17:15:00', '03:00:00', 6, 6),
('2025-07-07 12:10:00', '00:30:00', 7, 7),
('2025-07-08 16:40:00', '10:12:00', 8, 8),
('2025-07-09 20:20:00', '13:06:00', 9, 9),
('2025-07-10 11:05:00', '07:48:00', 10, 10),
('2025-07-11 13:30:00', '09:54:00', 11, 11),
('2025-07-12 19:45:00', '15:00:00', 12, 12),
('2025-07-13 08:50:00', '04:00:00', 13, 13),
('2025-07-14 18:35:00', '02:18:00', 14, 14),
('2025-07-15 14:10:00', '06:36:00', 15, 15),
('2025-07-16 10:00:00', '11:06:00', 16, 16),
('2025-07-17 21:40:00', '00:54:00', 17, 17),
('2025-07-18 22:50:00', '17:18:00', 18, 18),
('2025-07-19 09:00:00', '05:00:00', 19, 19),
('2025-07-20 07:30:00', '03:12:00', 20, 20),
('2025-07-21 13:00:00', '09:00:00', 21, 21),
('2025-07-22 17:00:00', '08:48:00', 22, 22),
('2025-07-23 20:10:00', '10:24:00', 23, 23),
('2025-07-24 12:30:00', '02:42:00', 24, 24),
('2025-07-25 14:45:00', '04:36:00', 25, 25),
('2025-07-26 15:15:00', '01:54:00', 26, 26),
('2025-07-27 18:25:00', '07:12:00', 27, 27),
('2025-07-28 08:20:00', '06:00:00', 28, 28),
('2025-07-29 11:55:00', '00:00:00', 29, 29),
('2025-07-30 16:10:00', '12:00:00', 30, 30);

-- Valores para a tabela pagamentos
INSERT INTO pagamentos (valor, data_pagamento, status, metodo_pagamento, id_usuario, id_jogo_adquirido) VALUES
(59.90, '2025-07-01 14:00:00', 'Pago', 'Cartao', 1, 1),
(39.99, '2025-07-02 15:30:00', 'Pago', 'Pix', 2, 2),
(49.00, '2025-07-03 11:45:00', 'Cancelado', 'Boleto', 3, 3),
(29.00, '2025-07-04 10:10:00', 'Pendente', 'Pix', 4, 4),
(69.00, '2025-07-05 16:00:00', 'Pago', 'Cartao', 5, 5),
(59.90, '2025-07-06 09:20:00', 'Pago', 'Saldo', 6, 6),
(19.90, '2025-07-07 13:50:00', 'Pendente', 'Boleto', 7, 7),
(25.00, '2025-07-08 17:30:00', 'Pago', 'Pix', 8, 8),
(45.00, '2025-07-09 12:15:00', 'Cancelado', 'Cartao', 9, 9),
(65.00, '2025-07-10 18:00:00', 'Pago', 'Cartao', 10, 10),
(15.90, '2025-07-11 08:40:00', 'Pendente', 'Pix', 11, 11),
(33.30, '2025-07-12 20:10:00', 'Pago', 'Boleto', 12, 12),
(42.00, '2025-07-13 21:00:00', 'Pago', 'Cartao', 13, 13),
(48.00, '2025-07-14 22:15:00', 'Cancelado', 'Pix', 14, 14),
(20.00, '2025-07-15 07:30:00', 'Pago', 'Saldo', 15, 15),
(56.60, '2025-07-16 06:25:00', 'Pago', 'Pix', 16, 16),
(69.90, '2025-07-17 19:10:00', 'Pendente', 'Cartao', 17, 17),
(38.50, '2025-07-18 13:00:00', 'Pago', 'Pix', 18, 18),
(19.90, '2025-07-19 09:00:00', 'Cancelado', 'Boleto', 19, 19),
(49.50, '2025-07-20 14:55:00', 'Pago', 'Cartao', 20, 20),
(45.00, '2025-07-21 16:40:00', 'Pago', 'Pix', 21, 21),
(54.99, '2025-07-22 17:25:00', 'Pendente', 'Cartao', 22, 22),
(27.30, '2025-07-23 10:35:00', 'Pago', 'Boleto', 23, 23),
(21.00, '2025-07-24 12:20:00', 'Pago', 'Pix', 24, 24),
(61.00, '2025-07-25 15:15:00', 'Cancelado', 'Cartao', 25, 25),
(67.00, '2025-07-26 09:10:00', 'Pago', 'Pix', 26, 26),
(22.22, '2025-07-27 18:45:00', 'Pago', 'Boleto', 27, 27),
(58.00, '2025-07-28 20:30:00', 'Pendente', 'Cartao', 28, 28),
(18.90, '2025-07-29 11:55:00', 'Pago', 'Pix', 29, 29),
(53.30, '2025-07-30 13:10:00', 'Pago', 'Cartao', 30, 30);

-- Valores para a tabela conquistas_adquiridas
INSERT INTO conquistas_adquiridas (data_adquirida, jogoAdquirido_idJogoAdquirido) VALUES
('2025-07-01 10:00:00', 1),
('2025-07-02 12:15:00', 2),
('2025-07-03 14:45:00', 3),
('2025-07-04 11:00:00', 4),
('2025-07-05 17:30:00', 5),
('2025-07-06 16:00:00', 6),
('2025-07-07 09:20:00', 7),
('2025-07-08 13:40:00', 8),
('2025-07-09 18:25:00', 9),
('2025-07-10 15:10:00', 10),
('2025-07-11 10:00:00', 11),
('2025-07-12 19:00:00', 12),
('2025-07-13 08:30:00', 13),
('2025-07-14 14:00:00', 14),
('2025-07-15 11:45:00', 15),
('2025-07-16 13:25:00', 16),
('2025-07-17 20:10:00', 17),
('2025-07-18 09:55:00', 18),
('2025-07-19 16:35:00', 19),
('2025-07-20 18:15:00', 20),
('2025-07-21 07:40:00', 21),
('2025-07-22 12:05:00', 22),
('2025-07-23 17:50:00', 23),
('2025-07-24 10:30:00', 24),
('2025-07-25 13:10:00', 25),
('2025-07-26 11:20:00', 26),
('2025-07-27 15:45:00', 27),
('2025-07-28 14:30:00', 28),
('2025-07-29 09:15:00', 29),
('2025-07-30 08:00:00', 30);

-- Valores para a tabela suporte
INSERT INTO suporte (data, descricao, usuarios_idUsuarios) VALUES
('2025-07-01 10:00:00', 'Não estou conseguindo acessar minha conta.', 1),
('2025-07-02 11:30:00', 'Fui cobrado por um jogo que não comprei.', 2),
('2025-07-03 14:15:00', 'Meu pagamento em pix não foi reconhecido.', 3),
('2025-07-04 15:45:00', 'Comprei um jogo e ele não apareceu na biblioteca.', 4),
('2025-07-05 09:40:00', 'Quero pedir reembolso de uma compra recente.', 5),
('2025-07-06 13:00:00', 'O jogo trava na hora de instalar.', 6),
('2025-07-07 12:25:00', 'Uma conquista não está sendo registrada.', 7),
('2025-07-08 07:10:00', 'Alguém acessou minha conta sem autorização.', 8),
('2025-07-09 18:20:00', 'Meu cartão é válido, mas está sendo recusado.', 9),
('2025-07-10 09:55:00', 'Desejo cancelar uma compra feita acidentalmente.', 10),
('2025-07-11 11:00:00', 'Código de autenticação não funciona.', 11),
('2025-07-12 13:30:00', 'Ganhei dois jogos iguais na biblioteca.', 12),
('2025-07-13 10:00:00', 'O launcher trava e fecha sozinho.', 13),
('2025-07-14 17:20:00', 'Cobrança mensal apareceu mesmo cancelando antes.', 14),
('2025-07-15 09:45:00', 'Gostaria de sugerir melhorias na interface.', 15),
('2025-07-16 14:00:00', 'O download para em 97% e reinicia.', 16),
('2025-07-17 12:50:00', 'Histórico de pedidos está em branco.', 17),
('2025-07-18 08:30:00', 'O jogo comprado não é compatível com meu sistema.', 18),
('2025-07-19 15:20:00', 'Boleto emitido com vencimento incorreto.', 19),
('2025-07-20 10:05:00', 'Como exporto meus saves?', 20),
('2025-07-21 13:00:00', 'Uso indevido do meu cartão cadastrado.', 21),
('2025-07-22 08:40:00', 'Recebo erro interno do servidor ao logar.', 22),
('2025-07-23 17:50:00', 'Alguns ícones estão distorcidos.', 23),
('2025-07-24 12:30:00', 'Quero recuperar meu perfil desativado.', 24),
('2025-07-25 14:20:00', 'Jogo está em idioma errado e não muda.', 25),
('2025-07-26 16:00:00', 'Mesma compra apareceu duas vezes.', 26),
('2025-07-27 10:10:00', 'Minha foto de perfil não aparece.', 27),
('2025-07-28 09:00:00', 'Código de cartão presente foi rejeitado.', 28),
('2025-07-29 07:30:00', 'Minhas preferências sumiram após atualizar.', 29),
('2025-07-30 08:00:00', 'Quero reportar que o jogo não roda no Linux.', 30);

-- Valores para a tabela avaliacoes
INSERT INTO avaliacoes (nota, comentario, jogo_idJogo, usuarios_idUsuarios, data_atualizacao) VALUES
(9.0, 'Excelente jogo, gráficos incríveis!', 1, 1, NOW()),
(7.5, 'Boa jogabilidade, mas enredo fraco.', 2, 2, NOW()),
(8.2, 'Me surpreendeu! Valeu a pena cada centavo.', 3, 3, NOW()),
(6.8, 'Legalzinho, mas esperava mais fases.', 4, 4, NOW()),
(9.5, 'Simplesmente épico. Me prendeu do início ao fim!', 5, 5, NOW()),
(5.5, 'Cheio de bugs. Precisa de atualização urgente.', 6, 6, NOW()),
(8.0, 'Muito divertido com os amigos!', 7, 7, NOW()),
(7.0, 'Campanha curta, mas multiplayer compensa.', 8, 8, NOW()),
(6.0, 'Gráficos bons, som ruim.', 9, 9, NOW()),
(9.2, 'História envolvente e jogabilidade fluida.', 10, 10, NOW()),
(8.5, 'Daria 10 se tivesse modo cooperativo.', 11, 11, NOW()),
(7.8, 'Conceito excelente, mas pouco conteúdo ainda.', 12, 12, NOW()),
(6.3, 'Tem potencial, mas trava muito.', 13, 13, NOW()),
(8.7, 'Narrativa emocionante com ótima trilha sonora.', 14, 14, NOW()),
(5.0, 'Achei genérico, enredo muito clichê.', 15, 15, NOW()),
(9.8, 'Jogo do ano pra mim, sem dúvidas!', 16, 16, NOW()),
(7.2, 'Razoável, pelo preço tá ok.', 17, 17, NOW()),
(9.0, 'Visual impecável, vale cada centavo.', 18, 18, NOW()),
(6.7, 'Faltou dublagem em português.', 19, 19, NOW()),
(8.6, 'Sistema de combate muito bom!', 20, 20, NOW()),
(7.3, 'Boa proposta, mas IA inimiga é fraca.', 21, 21, NOW()),
(8.9, 'Viciante! Não consigo parar de jogar.', 22, 22, NOW()),
(5.8, 'Enjoei rápido, muito repetitivo.', 23, 23, NOW()),
(7.4, 'Diversão garantida, mas curti mais o antecessor.', 24, 24, NOW()),
(9.4, 'Obra-prima. Jogabilidade e gráficos perfeitos.', 25, 25, NOW()),
(6.2, 'Pouco otimizado, FPS cai bastante.', 26, 26, NOW()),
(8.1, 'Valeu a experiência, bem imersivo.', 27, 27, NOW()),
(7.6, 'Modo história mediano, multiplayer é o ponto forte.', 28, 28, NOW()),
(9.1, 'A trilha sonora é maravilhosa!', 29, 29, NOW()),
(6.9, 'Bom jogo, mas termina rápido.', 30, 30, NOW());

-- Valores para a tabela categoria_has_jogo
INSERT INTO categoria_has_jogo (categoria_idCategoria, jogo_idJogo) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15),
(16, 16),
(17, 17),
(18, 18),
(19, 19),
(20, 20),
(21, 21),
(22, 22),
(23, 23),
(24, 24),
(25, 25),
(26, 26),
(27, 27),
(28, 28),
(29, 29),
(30, 30);

-- Valores para a tabela plataforma_has_jogo
INSERT INTO plataforma_has_jogo (plataforma_idPlataforma, jogo_idJogo) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15),
(16, 16),
(17, 17),
(18, 18),
(19, 19),
(20, 20),
(21, 21),
(22, 22),
(23, 23),
(24, 24),
(25, 25),
(26, 26),
(27, 27),
(28, 28),
(29, 29),
(30, 30);

-- Valores para a tabela amizade
INSERT INTO amizade (usuarios_idUsuariosMandou, usuarios_idUsuariosRecebeu, situacao) VALUES
(1, 2, 'Amigos'),
(2, 3, 'Pendente'),
(3, 4, 'Bloqueado'),
(4, 5, 'Recusado'),
(5, 6, 'Amigos'),
(6, 7, 'Amigos'),
(7, 8, 'Pendente'),
(8, 9, 'Bloqueado'),
(9, 10, 'Recusado'),
(10, 11, 'Amigos'),
(11, 12, 'Pendente'),
(12, 13, 'Bloqueado'),
(13, 14, 'Recusado'),
(14, 15, 'Amigos'),
(15, 16, 'Pendente'),
(16, 17, 'Bloqueado'),
(17, 18, 'Recusado'),
(18, 19, 'Amigos'),
(19, 20, 'Pendente'),
(20, 21, 'Bloqueado'),
(21, 22, 'Recusado'),
(22, 23, 'Amigos'),
(23, 24, 'Pendente'),
(24, 25, 'Bloqueado'),
(25, 26, 'Recusado'),
(26, 27, 'Amigos'),
(27, 28, 'Pendente'),
(28, 29, 'Bloqueado'),
(29, 30, 'Recusado'),
(30, 1, 'Amigos');


-- 3. Índices
-- Criação de pelo menos um índice (INDEX) em um atributo.
-- Incluir justificativa para a escolha do campo indexado, explicando o benefício em termos de desempenho.

-- Índice para acelerar buscas pelo apelido dos usuários
CREATE INDEX idx_usuarios_apelido ON usuarios(apelido);
SELECT * FROM usuarios WHERE apelido LIKE 'l%';
-- Índice para melhorar performance ao buscar jogos por preço
CREATE INDEX idx_jogo_preco ON jogo(preco);

-- 4. Scripts Adicionais (Inserir como Comentários no Arquivo)
-- Inclua os seguintes exemplos no arquivo .sql, comentados, como demonstração de domínio técnico:

-- Exclusão do banco de dados.
-- Este comando remove completamente o banco, incluindo todas as tabelas e dados.
-- Útil em ambientes de teste ou durante a redefinição completa do sistema.
DROP DATABASE nome_banco;

-- Exclusão de uma tabela.
-- Remove totalmente a estrutura e os dados da tabela especificada.
-- Ideal quando a tabela foi criada incorretamente ou deixou de ser necessária.
DROP TABLE nome_tabela;

-- Exclusão de uma coluna de uma tabela.
-- Remove uma coluna específica e seus dados de uma tabela já existente.
-- Útil para ajustes na modelagem quando um campo torna-se obsoleto.
ALTER TABLE usuarios DROP COLUMN saldo;

-- Adição de uma nova coluna (não prevista na modelagem inicial).
-- Permite estender a estrutura de uma tabela sem recriá-la.
-- Exemplo: adicionar campo "telefone" à tabela usuarios.
ALTER TABLE usuarios ADD telefone VARCHAR(15);

-- Edição de um registro específico.
-- Atualiza um valor em uma linha com base em uma condição.
-- Exemplo: alterar o status de um usuário com id = 5 para "Suspenso".
UPDATE usuarios SET status = 'Suspenso' WHERE id = 5;

-- Exclusão de um registro específico.
-- Remove uma linha de dados sem afetar a estrutura da tabela.
-- Exemplo: apagar um boleto com ID 10 da tabela boleto.
DELETE FROM boleto WHERE id = 10;

-- 5. Consultas SQL (Queries)
-- Caso sua modelagem não permita algum dos JOINs solicitados, incluir uma justificativa técnica.
-- As consultas devem utilizar, sempre que possível, os seguintes comandos SQL: WHERE e diferentes tipos de operadores; GROUP BY; HAVING e ORDER BY

-- Duas consultas simples, utilizando apenas uma tabela.

-- Listar todos os jogos com preço acima de R$50, ordenados pelo nome
SELECT nome, preco
FROM jogo
WHERE preco > 50
ORDER BY nome;

-- Contar quantos usuários estão com status 'Ativo'
SELECT COUNT(*) AS total_ativos
FROM usuarios
WHERE status = 'Ativo';

-- Quatro consultas com duas tabelas, utilizando INNER JOIN.
-- Exibir nome do usuário e nota dos jogos que ele avaliou
SELECT u.nome, a.nota
FROM usuarios u
INNER JOIN avaliacoes a ON u.id = a.usuarios_idUsuarios;

-- Mostrar nome do jogo e data de aquisição por usuário
SELECT j.nome, ja.data_aquisicao
FROM jogo j
INNER JOIN jogo_adquirido ja ON j.idJogo = ja.jogo_idJogo;

-- Listar jogos e suas respectivas categorias
SELECT j.nome AS jogo, c.nome AS categoria
FROM jogo j
INNER JOIN categoria_has_jogo cj ON j.idJogo = cj.jogo_idJogo
INNER JOIN categoria c ON cj.categoria_idCategoria = c.idCategoria;

-- Exibir nome do usuário e as conquistas adquiridas
SELECT u.nome, c.nome AS conquista
FROM usuarios u
INNER JOIN jogo_adquirido ja ON u.id = ja.usuarios_idUsuarios
INNER JOIN conquistas_adquiridas ca ON ja.id = ca.jogoAdquirido_idJogoAdquirido
INNER JOIN jogo j ON ja.jogo_idJogo = j.idJogo
INNER JOIN conquistas c ON j.conquistas_idConquistas = c.idConquistas;

-- Uma consulta com LEFT JOIN.
-- Listar todos os usuários, com informações de pagamentos (mesmo os que não têm pagamentos ainda)
SELECT u.nome, p.valor, p.status
FROM usuarios u
LEFT JOIN pagamentos p ON u.id = p.id_usuario;

-- Uma consulta com RIGHT JOIN.
-- Listar todos os pagamentos, exibindo o nome do cartão (se existir). Pagamentos sem cartão também devem aparecer.
SELECT p.id, p.valor, c.apelido_cartao
FROM pagamentos p
RIGHT JOIN cartao c ON p.id_cartao = c.id;

-- Uma consulta com três tabelas, utilizando qualquer tipo de JOIN.
-- Mostrar nome do usuário, nome do jogo adquirido e valor do pagamento
SELECT u.nome AS usuario, j.nome AS jogo, p.valor AS valor_pago
FROM usuarios u
INNER JOIN jogo_adquirido ja ON u.id = ja.usuarios_idUsuarios
INNER JOIN pagamentos p ON ja.id = p.id_jogo_adquirido   -- CORRIGIDO: utilização da coluna correta para o join
INNER JOIN jogo j ON ja.jogo_idJogo = j.idJogo;

-- Uma consulta com quatro tabelas, utilizando qualquer tipo de JOIN.
-- Exibir nome do usuário, nome do jogo, nome da categoria e plataforma associada
SELECT u.nome AS usuario, j.nome AS jogo, c.nome AS categoria, p.nome AS plataforma
FROM usuarios u
INNER JOIN jogo_adquirido ja ON u.id = ja.usuarios_idUsuarios
INNER JOIN jogo j ON ja.jogo_idJogo = j.idJogo
INNER JOIN categoria_has_jogo cj ON j.idJogo = cj.jogo_idJogo
INNER JOIN categoria c ON cj.categoria_idCategoria = c.idCategoria
INNER JOIN plataforma_has_jogo pj ON j.idJogo = pj.jogo_idJogo
INNER JOIN plataforma p ON pj.plataforma_idPlataforma = p.idPlataforma;

-- 6. Recursos Adicionais
-- Criação de três VIEWs.
-- View com nome dos jogos e sua média de nota nas avaliações
CREATE VIEW vw_media_avaliacoes AS
SELECT j.nome AS jogo, ROUND(AVG(a.nota), 2) AS media_nota
FROM jogo j
JOIN avaliacoes a ON j.idJogo = a.jogo_idJogo
GROUP BY j.nome;
-- ver a media
select *from vw_media_avaliacoes;

-- View que exibe usuários e os jogos que adquiriram
CREATE VIEW vw_usuarios_jogos AS
SELECT u.nome AS usuario, j.nome AS jogo, ja.data_aquisicao
FROM usuarios u
JOIN jogo_adquirido ja ON u.id = ja.usuarios_idUsuarios
JOIN jogo j ON ja.jogo_idJogo = j.idJogo;
-- ver os usuarios e os jogos adquiridos
select *from vw_usuarios_jogos;

-- View que mostra pagamentos com método e status
CREATE VIEW vw_pagamentos_resumo AS
SELECT p.id, u.nome AS usuario, p.valor, p.metodo_pagamento, p.status
FROM pagamentos p
JOIN usuarios u ON p.id_usuario = u.id;
select *from vw_pagamentos_resumo;

-- Criação de dois TRIGGERs, sendo:
-- Um BEFORE

-- Verifica se o valor do pagamento é maior que zero antes de inserir
DELIMITER //
CREATE TRIGGER trg_pagamento_valor_check
BEFORE INSERT ON pagamentos
FOR EACH ROW
BEGIN
  IF NEW.valor <= 0 THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Valor do pagamento deve ser maior que zero.';
  END IF;
END;
//
DELIMITER ;

-- Um AFTER

-- Atualiza data_atualizacao do usuário após edição.
-- Nota: Atualizar a mesma tabela em um trigger AFTER pode provocar erro de recursividade.
-- Para contornar, utiliza-se uma variável de sessão para impedir chamadas recursivas.
DELIMITER //
CREATE TRIGGER trg_atualiza_usuario_after
AFTER UPDATE ON usuarios
FOR EACH ROW
BEGIN
  IF (@disable_trigger IS NULL OR @disable_trigger = 0) THEN
    SET @disable_trigger = 1;
    UPDATE usuarios SET data_atualizacao = NOW() WHERE id = NEW.id;
    SET @disable_trigger = 0;
  END IF;
END;
//
DELIMITER ;

SHOW TRIGGERS FROM steam;


-- Criação de três usuários com permissões distintas.
-- Administrador total
CREATE USER 'admin_db'@'localhost' IDENTIFIED BY 'senhaAdmin123';
GRANT ALL PRIVILEGES ON steam.* TO 'admin_db'@'localhost' WITH GRANT OPTION;

-- Usuário com acesso apenas leitura
CREATE USER 'consulta_readonly'@'localhost' IDENTIFIED BY 'leitura456';
GRANT SELECT ON steam.* TO 'consulta_readonly'@'localhost';

-- Usuário com acesso apenas à tabela de pagamentos
CREATE USER 'pagamentos_user'@'localhost' IDENTIFIED BY 'paga789';
GRANT SELECT, INSERT, UPDATE ON steam.pagamentos TO 'pagamentos_user'@'localhost';

-- Revogação de privilégios de dois usuários
REVOKE ALL PRIVILEGES ON steam.* FROM 'consulta_readonly'@'localhost';
REVOKE ALL PRIVILEGES ON steam.* FROM 'admin_db'@'localhost';

-- Exclusão de um dos usuários criados.
DROP USER 'admin_db'@'localhost';
DROP USER 'consulta_readonly'@'localhost';
DROP USER 'pagamentos_user'@'localhost';

-- Exemplo de uso dos comandos:
-- COMMIT
-- Início da transação: inserção de uma nova categoria
START TRANSACTION;
INSERT INTO categoria (nome, descricao)
VALUES ('Experimental', 'Categoria criada para testes e inovações');
SELECT * FROM categoria;
COMMIT;

ROLLBACK;
SELECT * FROM categoria;

-- ROLLBACK
-- Início de outra transação: inserção de uma categoria para demonstrar rollback
START TRANSACTION;
-- (Exemplo de inserção que será revertida)
INSERT INTO categoria (nome, descricao)
VALUES ('Teste', 'Categoria para demonstrar rollback');
SELECT * FROM categoria;
ROLLBACK;







