# Refatoração do Sistema de Mercado em C
Este repositório é a refatoração de um sistema desenvolvido em C, utilizando o Visual Studio Code como ambiente de desenvolvimento, que simula o sistema de Mercado. 

Link do `Sistema de Mercado em C Oficial` para mais informações: https://github.com/Lukiwo0/Codigos-de-Programacao-da-Universidade/tree/main/C%C3%B3digos%20de%20Programa%C3%A7%C3%A3o%20da%20Universidade/Segundo%20Semestre/Estrutura%20de%20Dados/Sistema%20de%20Mercado%20-%20N1



## Plano de Refatoração

### Organização do Código:
- Coloque apenas as declarações das funções (protótipos) antes do main, deixando as implementações completas das funções após o main.

### Parâmetros de Ponteiro para Constante:
- Modifique os parâmetros que são apenas para leitura para serem ponteiros constantes. Isso aplica-se a variáveis como validar, prod, cont, e contcar.

### Separação de Declarações de Variáveis:
- Separe as declarações de múltiplas variáveis em linhas distintas para melhorar a legibilidade.

### Conversões de Tipos Explícitas:
- Revise as conversões de tipos, especialmente de float para int (e vice-versa), para garantir que nenhuma precisão importante é perdida. Utilize conversões explícitas onde for necessário.

### Evitar Sombreamento de Variáveis Globais:
- Certifique-se de que os nomes das variáveis locais não sejam iguais aos nomes de variáveis globais. Caso haja coincidências, renomeie as variáveis locais.

### Verificação de Condições para Valores Indefinidos:
- Verifique se todas as variáveis usadas em condições de controle estão devidamente inicializadas para evitar comportamentos inesperados.

### Remover Parâmetros Não Utilizados:
- Remova os parâmetros que não estão sendo usados nas funções, como prod e cont, em chamadas onde eles são desnecessários.

### Especificação de Largura de Campo para %s:
- Ajuste o uso do %s nas printf para limitar o número de caracteres, evitando problemas de buffer overflow e melhorando a segurança.
