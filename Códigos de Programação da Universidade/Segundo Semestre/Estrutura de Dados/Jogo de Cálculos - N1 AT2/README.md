# AT2 - N2 - Jogo de Cálculo

Este repositório contém o código do **Jogo de Cálculo**, desenvolvido em grupo durante a aula e foi criada sem acesso à internet. 

Posteriormente, refatorei e reescrevi o código de forma independente, sem consultar a versão original feita com o grupo, e adicionei novas funcionalidades e melhorias ao jogo.

## Link do Código

Você pode acessar o código da versão reescrita por mim, onde fiz as melhorias, neste link: [Código do Jogo de Cálculo](https://github.com/Lukiwo0/Estudos/tree/main/Jogo%20Calculo)


# Jogo de Operações Matemáticas

Este é um jogo interativo desenvolvido em C, onde o jogador escolhe diferentes níveis de dificuldade e realiza operações matemáticas (soma, subtração e multiplicação) para acumular pontos. O jogo gera operações aleatórias e avalia as respostas do jogador, fornecendo feedback sobre acertos ou erros.

## Descrição

O jogo oferece quatro níveis de dificuldade, variando a faixa dos números envolvidos nas operações matemáticas:
1. **Fácil**: Operações entre 0 e 10.
2. **Médio**: Operações entre 0 e 100.
3. **Difícil**: Operações entre 0 e 1000.
4. **Insano**: Operações entre 0 e 10000.

O jogador pode escolher o número de fases que deseja jogar, e a cada fase, o sistema gera uma operação aleatória (soma, subtração ou multiplicação). A cada acerto, o jogador acumula pontos, e ao final do jogo, o sistema apresenta o desempenho geral.

## Funcionalidades

- **Escolha de Nível de Dificuldade**: O jogador escolhe entre quatro níveis de dificuldade (Fácil, Médio, Difícil, Insano).
- **Operações Aleatórias**: O sistema gera operações aleatórias de soma, subtração ou multiplicação.
- **Pontuação**: O jogador acumula pontos com acertos e o desempenho é registrado.
- **Instruções**: O jogador pode acessar uma descrição do jogo e regras.
- **Desempenho**: O jogador pode ver o número de fases completadas, acertos e erros.

## Estrutura do Código

### Estruturas e Tipos

- **Estrutura `Calcular`**: Contém as variáveis usadas para controlar o estado do jogo, como a dificuldade, os valores das operações, o resultado da operação, a pontuação e o número de fases.

### Funções Principais

- `main()`: Função principal que inicia o jogo e exibe o menu.
- `menu()`: Exibe o menu principal onde o jogador pode escolher as opções.
- `jogar()`: Inicia a fase de jogo, onde o jogador escolhe quantas fases deseja jogar.
- `somar()`, `diminuir()`, `multiplicar()`: Funções que geram e avaliam operações matemáticas de soma, subtração e multiplicação, respectivamente.
- `instrucaoJogo()`: Exibe as instruções do jogo.
- `mostrarInfo()`: Exibe o desempenho do jogador (número de fases, acertos e erros).
- `validarInt()`: Função auxiliar para validar a entrada de números inteiros.

## Como Usar

### Compilação

1. Baixe o código fonte.
2. Compile o código com um compilador C, como o GCC. Exemplo de comando:

   ```bash
   gcc -o jogo main.c

```bash
Olá Jogador, espero que esteja bem!

1 - Instruções do Jogo
2 - Nível Fácil
3 - Nível Médio
4 - Nível Difícil
5 - Nível Insano
6 - Mostrar Desempenho
7 - Sair

Digite uma opção: 2

Fácil

Digite quantas fases deseja fazer: 3

Quanto é a soma de 4 + 5: 9
Correto!

Quanto é a soma de 3 + 7: 10
Errado! O resultado é: 10

...

Deseja fazer mais fases?
1 - Sim
2 - Não
Digite sua opção: 2
Saindo do programa...
```
