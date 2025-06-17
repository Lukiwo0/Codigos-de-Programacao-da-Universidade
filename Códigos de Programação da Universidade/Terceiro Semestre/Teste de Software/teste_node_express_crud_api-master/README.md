# API de Usuários - Testes Automatizados com Postman

Este repositório contém o projeto de testes de API REST utilizando a ferramenta Postman, desenvolvido como parte da disciplina de **Teste de Software** do curso de **Ciência da Computação (Parceria com o Porto Digital)** da **Universidade Católica de Brasília**.

---

## 📜 Descrição do Projeto

O objetivo deste trabalho é demonstrar o uso do Postman para testes de uma API construída em **Node.js** e **Express**. Foram testados os métodos HTTP **GET, POST, PUT, PATCH e DELETE** em diferentes cenários, tanto de sucesso quanto de falha, garantindo a qualidade e a confiabilidade do sistema.

A API simulada realiza operações **CRUD** (Create, Read, Update, Delete) de usuários, permitindo cadastrar, listar, atualizar e remover usuários.

---

## 📂 Estrutura do Repositório

```plaintext
.
├── postman/
│   ├── API_Test_Collection.postman_collection.json    # Coleção de requisições do Postman
│   ├── TESTE_API.postman_test_run.json                # Resultados de execução dos testes
├── src/                                               # Código-fonte da API (Node.js/Express)
│   ├── app.js
│   └── ...
└── README.md                                          # Este arquivo
```

---

## 🚀 Como Executar os Testes

### 1️⃣ Importar a coleção no Postman

- Abra o **Postman**.
- Vá em **Import**.
- Selecione o arquivo:  
  `postman/API_Test_Collection.postman_collection.json`.

### 2️⃣ Executar os testes

- Abra a aba **Collection Runner** no Postman.
- Escolha a coleção **API Test Collection**.
- Clique em **Run** para executar todos os testes de forma sequencial.

---

## 📝 Relatório de Testes

- Os resultados dos testes (status dos endpoints, asserções e logs) foram exportados para:  
  `postman/TESTE_API.postman_test_run.json`.

- Esse arquivo pode ser reimportado no Postman (aba **Runner** → **Import Results**) ou processado por outras ferramentas, como **Newman** ou **Newman HTML Reporter**, para gerar relatórios em HTML.

---

## 🔬 Cenários Testados

Foram validados dois grupos principais de cenários:

✅ **Cenários com Credenciais Válidas**  
- **GET /**: Verifica se a API responde com a mensagem de boas-vindas.  
- **GET /people**: Retorna lista de usuários.  
- **POST /people**: Cadastra novo usuário.  
- **GET /people/:id**: Busca usuário por ID.  
- **PATCH /people/:id**: Atualiza parcialmente dados de usuário.  
- **PUT /people/:id**: Substitui completamente dados de usuário.  
- **DELETE /people/:id**: Remove usuário do sistema.

❌ **Cenários com Credenciais Inválidas**  
- Tentativas de acesso a rotas inexistentes.  
- Dados incompletos ou incorretos no corpo das requisições.  
- Exclusão ou atualização de usuários inexistentes.

---

## ⚙️ Tecnologias Utilizadas

- **Node.js**  
- **Express**  
- **Postman**  
- **JavaScript**  
- **JSON**

---

## 📝 Licença

Este projeto é disponibilizado apenas para fins acadêmicos e de aprendizado.

---
