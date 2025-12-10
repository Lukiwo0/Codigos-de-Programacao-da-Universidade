
USE steam;

---------------------------------------------------
-- Tabela: usuarios
---------------------------------------------------
-- Consulta 1: Seleciona todos os usuários
SELECT * FROM usuarios;
 
-- Consulta 2: Seleciona os usuários que estão ativos (Status = 'Ativo')
SELECT id, Nome, CPF, Apelido, Status 
FROM usuarios
WHERE Status = 'Ativo';
 
-- Consulta 3: Conta quantos usuários há para cada status
SELECT Status, COUNT(*) AS total
FROM usuarios
GROUP BY Status;

-- Consulta 4: Exemplo de inserção de um novo usuário
INSERT INTO usuarios (Nome, CPF, Apelido, Senha, Status, Saldo, data_nascimento)
VALUES ('Teste Usuario', '00011122233', 'teste', 'senhaTeste', 'Ativo', 50.00, '2000-01-01');

-- Consulta 5: Exemplo de atualização (ex.: aumentar o saldo do usuário com id = 1)
UPDATE usuarios
SET Saldo = Saldo + 100
WHERE id = 1;

-- Consulta 6: Atualiza o registro do usuário com id = 10, marcando-o como deletado e registrando a data/hora da deleção
UPDATE usuarios
SET deletado = 1, data_delecao = NOW()
WHERE id = 10;

-- Consulta 7: Seleciona os usuários que foram marcados como deletados (deletado = 1)
SELECT *
FROM usuarios
WHERE deletado = 1;

-- Consulta 8: Recupera o usuário com id = 10: define o campo 'deletado' como 0 (ativo) e remove a data de deleção
UPDATE usuarios
SET deletado = 0, data_delecao = NULL
WHERE id = 10;

---------------------------------------------------
-- Tabela: pix
---------------------------------------------------
-- Consulta 1: Seleciona todos os registros da tabela pix
SELECT * FROM pix;

-- Consulta 2: Busca registros em que a chave_pix contenha um "@" (por exemplo, e-mail)
SELECT id, chave_pix, id_transacao_pix
FROM pix
WHERE chave_pix LIKE '%@%';


---------------------------------------------------
-- Tabela: boleto
---------------------------------------------------
-- Consulta 1: Seleciona todos os boletos
SELECT * FROM boleto;

-- Consulta 2: Exibe boletos com vencimento nos próximos 7 dias
SELECT id, numero_boleto, data_vencimento
FROM boleto
WHERE data_vencimento BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 7 DAY);


---------------------------------------------------
-- Tabela: cartao
---------------------------------------------------
-- Consulta 1: Seleciona todos os cartões cadastrados
SELECT id, numero, Validade, cvv, tipo, Bandeira, Usuarios_idUsuarios, ultimos_digitos, nome_titular, apelido_cartao, data_adicao
FROM cartao;

-- Consulta 2: Seleciona os cartões de um usuário específico (por exemplo, usuários com id = 3)
SELECT id, numero, Validade, tipo, Bandeira
FROM cartao
WHERE Usuarios_idUsuarios = 3;


---------------------------------------------------
-- Tabela: pagamentos
---------------------------------------------------
-- Consulta 1: Seleciona todos os pagamentos
SELECT id, Valor, data_pagamento, Status, metodo_pagemento, id_usuario, id_jogo_adquirido, id_cartao, id_pix, id_boleto
FROM pagamentos;

-- Consulta 2: Lista os pagamentos efetuados via 'Cartao'
SELECT id, Valor, data_pagamento, Status, metodo_pagemento
FROM pagamentos
WHERE metodo_pagemento = 'Cartao';

-- Consulta 3: Exibe o total pago por cada usuário
SELECT id_usuario, SUM(Valor) AS total_pago
FROM pagamentos
GROUP BY id_usuario;


---------------------------------------------------
-- Tabela: suporte
---------------------------------------------------
-- Consulta 1: Seleciona todos os tickets de suporte
SELECT idSuporte, Data, Descricao, Usuarios_idUsuarios
FROM suporte;

-- Consulta 2: Exibe os tickets de suporte de um usuário específico (ex.: usuário com id = 5)
SELECT idSuporte, Data, Descricao
FROM suporte
WHERE Usuarios_idUsuarios = 5;


---------------------------------------------------
-- Tabela: conquistas
---------------------------------------------------
-- Consulta 1: Seleciona todas as conquistas
SELECT idConquistas, Nome, tipos_conquistas
FROM conquistas;

-- Consulta 2: Conta o número de conquistas para cada tipo
SELECT tipos_conquistas, COUNT(*) AS total
FROM conquistas
GROUP BY tipos_conquistas;


---------------------------------------------------
-- Tabela: jogo
---------------------------------------------------
-- Consulta 1: Seleciona todos os jogos
SELECT idJogo, Nome, Descricao, Preco, Conquistas_idConquistas, data_atualizacao
FROM jogo;

-- Consulta 2: Exibe jogos com preço acima de R$50
SELECT idJogo, Nome, Preco
FROM jogo
WHERE Preco > 50;

-- Consulta 3: Exibe os jogos de uma conquista específica (ex.: Conquistas_idConquistas = 2)
SELECT idJogo, Nome, Preco
FROM jogo
WHERE Conquistas_idConquistas = 2;

-- Consulta 4: Atualiza o registro do jogo com idJogo = 5, definindo deletado = 1 e a data da deleção
UPDATE jogo
SET deletado = 1, data_delecao = NOW()
WHERE idJogo = 5;

-- Consulta 5: Seleciona os jogos que foram marcados como deletados
SELECT *
FROM jogo
WHERE deletado = 1;

-- Consulta 6: Seleciona os jogos que não foram marcados como deletados
SELECT *
FROM jogo
WHERE deletado = 0;

-- Consulta 7: Recupera o jogo com idJogo = 5, voltando-o ao estado ativo
UPDATE jogo
SET deletado = 0, data_delecao = NULL
WHERE idJogo = 5;

---------------------------------------------------
-- Tabela: jogo_adquirido
---------------------------------------------------
-- Consulta 1: Seleciona todos os jogos adquiridos
SELECT id, data_aquisicao, horas_jogadas, Usuarios_idUsuarios, Jogo_idJogo
FROM jogo_adquirido;

-- Consulta 2: Exibe os jogos adquiridos por um usuário específico (ex.: usuário com id = 4)
SELECT id, data_aquisicao, horas_jogadas
FROM jogo_adquirido
WHERE Usuarios_idUsuarios = 4;

-- Consulta 3: Atualiza o registro do jogo_adquirido com id = 3 para exclusão lógica
UPDATE jogo_adquirido
SET deletado = 1, data_deletado = NOW()
WHERE id = 3;

-- Consulta 4: Seleciona os registros de jogo_adquirido que foram marcados como deletados
SELECT *
FROM jogo_adquirido
WHERE deletado = 1;

-- Consulta 5: Seleciona os registros de jogo_adquirido que não foram marcados como deletados
SELECT *
FROM jogo_adquirido
WHERE deletado = 0;

-- Consulta 6: Recupera o registro de jogo_adquirido com id = 3
UPDATE jogo_adquirido
SET deletado = 0, data_deletado = NULL
WHERE id = 3;

---------------------------------------------------
-- Tabela: conquistas_adquiridas
---------------------------------------------------
-- Consulta 1: Seleciona todas as conquistas adquiridas
SELECT idConquistasAdquiridas, data_adquirida, JogoAdquirido_idJogoAdquirido
FROM conquistas_adquiridas;

-- Consulta 2: Exibe as conquistas adquiridas para um jogo adquirido específico (ex.: id = 3)
SELECT idConquistasAdquiridas, data_adquirida
FROM conquistas_adquiridas
WHERE JogoAdquirido_idJogoAdquirido = 3;


---------------------------------------------------
-- Tabela: avaliacoes
---------------------------------------------------
-- Consulta 1: Seleciona todas as avaliações
SELECT idAvaliacoes, Nota, Comentario, Jogo_idJogo, Usuarios_idUsuarios, data_atualizacao
FROM avaliacoes;

-- Consulta 2: Exibe as avaliações de um jogo específico (ex.: Jogo_idJogo = 5)
SELECT idAvaliacoes, Nota, Comentario, data_atualizacao
FROM avaliacoes
WHERE Jogo_idJogo = 5;

-- Consulta 3: Calcula a média das notas para cada jogo
SELECT Jogo_idJogo, ROUND(AVG(Nota), 2) AS MediaNota
FROM avaliacoes
GROUP BY Jogo_idJogo;

-- Consulta 4: Atualiza a avaliação com idAvaliacoes = 7 (para um jogo específico, se a PK for composta, especifique também Jogo_idJogo)
UPDATE avaliacoes
SET deletado = 1
WHERE idAvaliacoes = 7 AND Jogo_idJogo = 2;

-- Consulta 5: Seleciona as avaliações que foram marcadas como deletadas
SELECT *
FROM avaliacoes
WHERE deletado = 1;

-- Consulta 6: Seleciona as avaliações que ainda não foram marcadas como deletadas
SELECT *
FROM avaliacoes
WHERE deletado = 0;

-- Consulta 7: Recupera a avaliação (por exemplo, com idAvaliacoes = 7 em um jogo específico)
UPDATE avaliacoes
SET deletado = 0
WHERE idAvaliacoes = 7 AND Jogo_idJogo = 2;

---------------------------------------------------
-- Tabela: categoria
---------------------------------------------------
-- Consulta 1: Seleciona todas as categorias
SELECT idCategoria, Nome, Descricao
FROM categoria;

-- Consulta 2: Exibe todas as categorias ordenadas por nome
SELECT idCategoria, Nome, Descricao
FROM categoria
ORDER BY Nome;


---------------------------------------------------
-- Tabela: plataforma
---------------------------------------------------
-- Consulta 1: Seleciona todas as plataformas
SELECT idPlataforma, Nome, Descricao
FROM plataforma;

-- Consulta 2: Lista as plataformas e os respectivos IDs
SELECT idPlataforma, Nome
FROM plataforma;


---------------------------------------------------
-- Tabela: plataforma_has_jogo
---------------------------------------------------
-- Consulta 1: Seleciona todas as relações entre jogos e plataformas
SELECT Plataforma_idPlataforma, Jogo_idJogo
FROM plataforma_has_jogo;

-- Consulta 2: Exibe o nome do jogo junto com o nome da plataforma
SELECT j.Nome AS Jogo, p.Nome AS Plataforma
FROM plataforma_has_jogo ph
JOIN jogo j ON ph.Jogo_idJogo = j.idJogo
JOIN plataforma p ON ph.Plataforma_idPlataforma = p.idPlataforma;


---------------------------------------------------
-- Tabela: categoria_has_jogo
---------------------------------------------------
-- Consulta 1: Seleciona todas as relações entre jogos e categorias
SELECT Categoria_idCategoria, Jogo_idJogo
FROM categoria_has_jogo;

-- Consulta 2: Exibe o nome do jogo junto com o nome da categoria
SELECT j.Nome AS Jogo, c.Nome AS Categoria
FROM categoria_has_jogo ch
JOIN jogo j ON ch.Jogo_idJogo = j.idJogo
JOIN categoria c ON ch.Categoria_idCategoria = c.idCategoria;


---------------------------------------------------
-- Tabela: amizade
---------------------------------------------------
-- Consulta 1: Seleciona todas as amizades
SELECT idAmizade, Usuarios_idUsuariosMandou, Usuarios_idUsuariosRecebeu, situacao
FROM amizade;

-- Consulta 2: Exibe as amizades onde um usuário específico está envolvido (ex.: id = 2)
SELECT idAmizade, Usuarios_idUsuariosMandou, Usuarios_idUsuariosRecebeu, situacao
FROM amizade
WHERE Usuarios_idUsuariosMandou = 2 OR Usuarios_idUsuariosRecebeu = 2;

-- Consulta 3: Conta o número de amizades agrupado pela situação (ex.: Amigos, Pendente, etc.)
SELECT situacao, COUNT(*) AS total
FROM amizade
GROUP BY situacao;
