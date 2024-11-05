# Sistema de Mercado em C
Este repositório é um sistema desenvolvido em C, utilizando o Visual Studio Code como ambiente de desenvolvimento, que simula o sistema de Mercado. 

## Funcionalidades Implementadas

### Validação de Dados
- **`limparBuffer`**: Limpa o buffer de entrada para evitar leituras indesejadas.
- **`validarNumInt()`**: Valida se a entrada é um número inteiro.
- **`validarNumFloat()`**: Valida se a entrada é um número decimal (float).
- **`validarNome()`**: Valida se a entrada é um nome que tem mais de 1 caractere.
- **`validarOpcao()`**: Verifica se a opção escolhida pelo usuário tem apenas 1 caractere.

### Gerenciamento de Produtos
- **`listarProduto()`**: Lista todos os produtos cadastrados.
- **`visualizarListaProduto()`**: Pergunta se o usuário quer ver a lista de produtos.
- **`cadastrarProduto()`**: Cadastrar novos produtos.
- **`removerProduto()`**: Remove um produto específico cadastrado.
- **`excluirTodosProdutos()`**: Exclui todos os produtos cadastrados.
- **`infoProduto()`**: Mostra informações detalhadas sobre um produto específico.

### Gerenciamento do Carrinho
- **`listarCarrinho()`**: Lista os produtos atualmente no carrinho.
- **`visualizarCarrinho()`**: Pergunta se o usuário quer ver os produtos que tem no carrinho.
- **`adicionarCarrinho()`**: Adiciona um produto ao carrinho.
- **`removerdoCarrinho()`**: Remove um produto do carrinho.
- **`fecharCarrinho()`**: Finaliza a compra.
- **`cancelarCarrinho()`**: Cancela a compra e esvazia o carrinho.
- **`temNoCarrinho()`**: Verifica se há um produto específico no carrinho.

### Acesso e Navegação
- **`areaAdmin()`**: Gerencia as operações disponíveis na área administrativa.
- **`areaCliente()`**: Gerencia as operações disponíveis na área do cliente.
- **`verificarAcesso()`**: Controla o acesso à área administrativa.
- **`menu()`**: Exibe o menu principal e gerencia a navegação entre as áreas.

### Função Principal
- **`main()`**: Função principal que inicializa o sistema e chama o **`menu()`**.

## Estruturação do Código

O código do sistema de gerenciamento de supermercado foi organizado de maneira para facilitar a leitura, manutenção e futuras melhorias/incrementações. 
Abaixo, detalhamos a estrutura utilizada:

### Organização em Funções

O código foi dividido em diversas funções, cada uma com uma responsabilidade específica. Isso segue o princípio da **programação modular**, permitindo que cada função realize uma tarefa específica, o que facilita a identificação de problemas e a implementação de novas funcionalidades. As funções estão agrupadas em categorias conforme suas funcionalidades:

1. **Validação de Dados**: Funções como `validarNumInt`, `validarNumFloat`, `validarNome`, e `validarOpcao` são responsáveis por garantir que as entradas do usuário sejam válidas antes de prosseguir com a execução do programa. Isso ajuda a evitar erros e exceções indesejadas.

2. **Gerenciamento de Produtos**: Funções como `cadastrarProduto`, `removerProduto`, `listarProduto`, e `infoProduto` cuidam de todas as operações relacionadas aos produtos. Essa separação permite que o código relacionado a produtos seja facilmente encontrado e modificado.

3. **Gerenciamento do Carrinho**: Funções como `adicionarCarrinho`, `removerdoCarrinho`, e `fecharCarrinho` tratam das operações realizadas no carrinho de compras, permitindo ao usuário adicionar ou remover produtos e finalizar compras.

4. **Acesso e Navegação**: Funções como `areaAdmin`, `areaCliente`, e `menu` gerenciam a navegação entre diferentes áreas do sistema, garantindo que os usuários acessem as funcionalidades apropriadas conforme suas permissões.

### Uso de Estruturas de Dados

O sistema utiliza estruturas de dados (typedef struct) como `Produto` e `Carrinho` para representar informações sobre produtos e itens no carrinho de compras. Essas estruturas são definidas de forma a incluir todos os atributos necessários para as operações do sistema, como código do produto, nome, preço e quantidade. Isso organiza os dados de forma lógica e facilita a manipulação.

### Boas Práticas

- **Nomenclatura Clara**: As funções e variáveis foram nomeadas de forma descritiva para refletir claramente sua funcionalidade. Isso ajuda outros desenvolvedores a entender rapidamente o propósito de cada parte do código.
  
- **Comentários**: O código contém comentários explicativos que descrevem o funcionamento das funções e a lógica por trás de operações específicas, o que facilita a compreensão do código para quem o lê.

- **Tratamento de Erros**: O código implementa verificações para garantir que as entradas do usuário sejam válidas e que operações indesejadas sejam evitadas, aumentando a robustez do sistema.

## Como Executar?
Compile o código com um compilador para linguagem C/C++ e depois execute-o.
Se quiser testar on-line segue um link onde é só executar o código: https://onlinegdb.com/Zv6rX9WbCc

## Contribuições

Contribuições são bem-vindas! 
Se você deseja sugerir melhorias ou reportar bugs, sinta-se à vontade para abrir um issue ou pull request.

## Futuras melhorias/incrementações 
- Integração com um banco de dados.
- Editar as informações de um produto cadastrado
- Tentar criar uma interface.
- Perguntar se deseja cadastrar mais de um produto quando entrar no **`cadastrarProduto()`**.
- Perguntar se deseja adicionar mais de um produto no Carrinho quando entrar no **`adicionarCarrinho()`**.