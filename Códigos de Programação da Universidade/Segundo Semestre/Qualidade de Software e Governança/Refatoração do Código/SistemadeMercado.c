/*
    No switch-case das opções, optei por usar 'char' em vez de 'int'.

    Usar 'char' para as opções, facilita o tratamento da entrada do usuário.
    Isso permite que o programa reconheça facilmente caracteres individuais,
    como '1', '2' e '3', sem precisar de conversões adicionais.
    Isso facilita o tratamento da opção escolhida pelo usuário, 
    tornando o código mais legível e simples.
*/
/*
    A função validarOpcao é muito importante!
    Se mais de um caractere for enviado ao switch-case, comportamentos indesejados podem ocorrer.

    Exemplo 1:
    - Se o usuário digitar '1e':
      O programa lê o primeiro caractere '1' e o envia para o case '1',
      ignorando o 'e' digitado.

    Exemplo 2:
    - Se o usuário digitar 'e1':
      O programa lê o primeiro caractere 'e' e vai para o default. 
      Em seguida, ele continua a ler o próximo caractere '1', 
      o que pode causar confusão e resultados inesperados.

*/
/*
    Nas variaveis, optei por usar 'char' em vez de 'int' ou 'float'.

    Usar 'char', evita o sistema da erro e encerrar, se o usuario colocar um digito não numérico (0-9).
    Só pegar esse 'char' que é pra ser um 'int' ou 'float' e validar para não tem digito não numérico (0-9).
    Por isso criei as funções validarNumInt e validarNumFloat.
*/

// Importando as bibliotecas
#include <stdio.h> // para utilizar entrada/saída (printf, scanf)
#include <stdlib.h> // para converter uma string em int ou float (atoi, atof)
#include <stdbool.h> // para utilizar variaveis do tipo booleana (bool)
#include <string.h> // para manipulação de strings (strlen, strcpy)
#include <ctype.h> // para classificação de caracteres (isdigit)
#include <windows.h> // específica do Windows para manipulação de funções do sistema operacional (SetConsoleOutputCP)

#define MAX 30 // tamanho maximo da array de caracteres do char nome
#define MAX_PROD 51 // Tamanho maximo da array: int global_cont e global_contcar

// Variaveis globais
int global_cont = 0; // para armazenar a quantidade de produtos cadastrados.
int global_contcar = 0; //para armazenar a quantidade de produtos dentro do carrinho.

// Armazenar os dados dos produtos
typedef struct { 
    int codigo; // para armazenar o código do produto
    char nome[MAX]; // para armazenar o nome do produto
    float preco; // para armazenar o preço do produto
} Produto;
// Armazenar os dados do carrinho
typedef struct { 
    Produto produto; // para armazenar o produto no carrinho
    int quantidade; // para armazenar a quantidade deste produto
} Carrinho; 


// Protótipos de Funções
void limparBuffer();

bool validarNumInt(char *validar);
bool validarNumFloat(char *validar);
bool validarNome(char *validar);
bool validarOpcao(char *escolha);

void menu(Produto prod[MAX_PROD], int *global_cont, Carrinho car[MAX_PROD], int *global_contcar);

void areaAdmin (Produto prod[MAX_PROD], int *global_cont, Carrinho car[MAX_PROD], int *global_contcar);
void verificarAcesso (Produto prod[MAX_PROD], int *global_cont, Carrinho car[MAX_PROD], int *global_contcar);
void cadastrarProduto (Produto prod[MAX_PROD], int *global_cont);
void removerProduto (Produto prod[MAX_PROD], int *global_cont);
void excluirTodosProdutos (Produto prod[MAX_PROD], int *global_cont);

void listarProduto (Produto prod[MAX_PROD], const int *global_cont);
void visualizarListaProduto (Produto prod[MAX_PROD], const int *global_cont);
void infoProduto (Produto prod[MAX_PROD], const int *global_cont);

void areaCliente (Produto prod[MAX_PROD], int *global_cont, Carrinho car[MAX_PROD], int *global_contcar);
void adicionarCarrinho (Produto prod[MAX_PROD], int *global_cont, Carrinho car[MAX_PROD], int *global_contcar);
void removerdoCarrinho (Produto prod[MAX_PROD], int *global_cont, Carrinho car[MAX_PROD], int *global_contcar);
void visualizarCarrinho (Produto prod[MAX_PROD], const int *global_cont, Carrinho car[MAX_PROD], int *global_contcar);
void listarCarrinho (Produto prod[MAX_PROD], const int *global_cont, Carrinho car[MAX_PROD], int *global_contcar);
void cancelarCarrinho (int *global_contcar);
void fecharCarrinho (Produto prod[MAX_PROD], int *global_cont, Carrinho car[MAX_PROD], int *global_contcar);
void temNoCarrinho(Carrinho car[MAX_PROD], const  int *global_contcar);



int main() {
    SetConsoleOutputCP(CP_UTF8); // Configura a saída do console para suportar a codificação UTF-8, permitindo a exibição correta de caracteres especiais, como o acento.

    Produto prod[MAX_PROD]; // Declara um array de produtos, permitindo o armazenamento de até MAX_PROD produtos.
    Carrinho car[MAX_PROD]; // Declara um array de carrinhos, permitindo o armazenamento de até MAX_PROD itens no carrinho.

    menu(prod, &global_cont, car, &global_contcar); // Chama a função menu, passando as informações dos produtos e do carrinho.
    printf("Saindo do sistema...\n");

    return 0;
}




// Função para limpar o Buffer
void limparBuffer() {
    int c;
    while ((c = getchar()) != '\n' && c != EOF); // lê até a nova linha (\n) ou o fim do arquivo (EOF) e remove os caracteres.
}



// Funções para validar:
//  Como várias partes do código exigem a validação de números inteiros,
//  números em ponto flutuante (float), nomes de produtos e opções
//  escolhidas pelo usuário, foram criadas funções de validação.
//  Isso facilita a manutenção, acelera o desenvolvimento e reduz a
//  duplicação de código, tornando-o mais eficiente e organizado.
// Função para validar números inteiros  
bool validarNumInt(char *validar) {
    // Se o valor de um int for = 0 -> false // Se o valor for = 1 -> true
    // Variaveis para verificar se tem letra e se o número é valido
    int letra = 0; 
    int numero = 0;

    for (int i = 0; i < strlen(validar); i++) { //  percorre cada caractere
        if (!isdigit(validar[i])) { // Verifica se o caractere não for um digito numérico (0-9)
            letra++; // Adiciona +1 no valor do int letra
            break; // Encerra o loop do for
        }
    }

    int num = atoi(validar); // Converte a string 'validar' para um número inteiro e atribui esse valor no int num
    if (num <= 0) { // Verifica se o valor do num é igual ou menor que 0
        numero++; // Adiciona +1 no valor do int numero
    }

    if (letra == 1) {
        printf("\nErro! \nCódigo inválido, não pode ter letra! \nApenas número inteiro.\n\n");
        return true; // Retorna verdadeiro(true) se houver uma letra
    } else if (numero == 1) { 
        printf("\nErro! \nCódigo inválido, não pode ser menor ou igual a 0!\n\n");
        return true; // Retorna verdadeiro(true) se o número for inválido
    } else {
        return false; // Retorna falso(false) se não houver erros
    }
}

// Função para validar números float 
bool validarNumFloat(char *validar) {
    // Se o valor de um int for = 0 -> false // Se o valor for = 1 -> true
    // Variaveis para verificar se tem letra e se o número é valido
    int ponto = 0;
    int letra = 0;
    int numero = 0;

    for (int i = 0; i < strlen(validar); i++) {
        if (!isdigit(validar[i])) { // Verifica se o caractere não for um digito numérico (0-9)
            if(validar[i] == '.' && ponto == 0) { // Permitir ter um '.', Permite -> 13.50, Não permite -> 1.000.50
                ponto++; // Adiciona +1 no valor do int ponto
            } else {
               letra++; // Adiciona +1 no valor do int letra
               break;  // Encerra o loop do for
            }
        }
    }

    int num = atof(validar); // Converte a string 'validar' para um número float e atribui esse valor no int num
    if (num <= 0) { // Verifica se o valor do num é igual ou menor que 0
        numero++; // Adiciona +1 no valor do int numero
    }

    if (letra == 1) {
        printf("\nErro! \nCódigo inválido, não pode ter letra! \nApenas número.1\n\n");
        return true; // Retorna verdadeiro(true) se houver uma letra
    } else if (numero == 1) {
        printf("\nErro! \nO preço do Produto não pode ser negativo e precisa ser maior que R$0,00.\n\n");
        return true; // Retorna verdadeiro(true) se o número for inválido
    } else {
        return false; // Retorna falso(false) se não houver erros
    }
}

// Função para validar nome do produto 
bool validarNome(char *validar) {
    return strlen(validar) <= 1; // Retorna verdadeiro(true) se o nome tiver 1 ou menos caractere
}

// Função para validar a opção que o usuario escolheu
bool validarOpcao(char *escolha) {
    // Explicação a partir da linha 10 por que a função validarOpcao é muito importante!
    return strlen(escolha) != 1; // Retorna verdadeiro(true) se a opção não tiver exatamente 1 caractere
}



// Função menu, para conseguir ir da area admin para a areaCliente e virse versa, e sair também do sistema.
void menu(Produto prod[MAX_PROD], int *global_cont, Carrinho car[MAX_PROD], int *global_contcar) {
    bool sair = false;
    char escolha[3];

    do {
        printf("\n\tBem vindo ao Sistema do Supermercado UCB\n\n");

        printf("1 - Área do Cliente\n2 - Área do Administrador\n3 - Sair\n");
        printf("Escolha uma opção: ");
        scanf(" %2s", escolha); // Explicação a partir da linha 2 sobre por que optei por usar 'char' em vez de 'int'.

        if(validarOpcao(escolha)) { // Explicação a partir da linha 10 por que a função validarOpcao é muito importante!
            printf("\nErro! O sistema só aceita o envio de um caractere\nExemplo:\n3 = tem um caractere\n13, 35435, 930200 = tem mais de um caractere\n");
            limparBuffer();
            continue;
        }

        switch (escolha[0]) {
        case '1':
            areaCliente(prod, global_cont, car, global_contcar);
            break;

        case '2':
            verificarAcesso(prod, global_cont, car, global_contcar);
            break;

        case '3':
            sair = true;
            break;

        default:
            printf("\nErro! Opção inválida.\n");
            limparBuffer();
            break;
        }

    } while (!sair);
}



// Função do Administrador, acesso restrito
void areaAdmin (Produto prod[MAX_PROD], int *global_cont, Carrinho car[MAX_PROD], int *global_contcar) {
    bool sair = false;
    char escolha[3];

    do {
        printf("\n\tEntrou na área do Admin\n");
        printf("1 - Cadastrar Produto\n2 - Listar Produtos\n3 - Ver informações de um produto\n4 - Remover Produtos\n5 - Excluir todos os Produtos\n6 - Voltar\n");
        printf("Escolha uma opção: ");
        scanf(" %2s", escolha); // Explicação a partir da linha 2 sobre por que optei por usar 'char' em vez de 'int'.

        if(validarOpcao(escolha)) { // Explicação a partir da linha 10 por que a função validarOpcao é muito importante!
            printf("\nErro! O sistema só aceita o envio de um caractere\nExemplo:\n3 = tem um caractere\n13, 35435, 930200 = tem mais de um caractere\n");
            continue;
        }

        switch (escolha[0]) {
        case '1':
            cadastrarProduto(prod, global_cont);
            break;

        case '2':
            listarProduto(prod, global_cont);
            break;

        case '3':
            infoProduto(prod, global_cont);
            break;

        case '4':
            removerProduto(prod, global_cont);
            break;
        
        case '5':
            excluirTodosProdutos(prod, global_cont);
            break;

        case '6':
            sair = true;
            break;

        default:
            printf("\nErro! Opção inválida.\n");
            limparBuffer();
            break;
        }
    } while(!sair);
}

// Função para acessar a areaAdmin, que é um área restrita, pois
// permite adicionar, remover produtos e excluir todos produtos.
// Por isso precisa fazer um login.
// Durante os testes do sistema, o usuário e a senha são visíveis no código no if,
// e são exibidos em um printf para facilitar o acesso.
void verificarAcesso (Produto prod[MAX_PROD], int *global_cont, Carrinho car[MAX_PROD], int *global_contcar) {
    char user[11];
    char senha[11];
    printf("\nAviso!\nLocal restrito, necessario fazer login\nUsuario do admin: 'admin'\nSenha: 'admin123'\n\n");
    printf("Digite o usuário do Admin: ");
    scanf(" %10s", user);
    printf("Digite a senha do Admin: ");
    scanf(" %10s", senha);

    if (strcmp(user, "admin") == 0 && strcmp(senha, "admin123") == 0) {
        printf("Acesso permitido.\n");
        areaAdmin(prod, global_cont, car, global_contcar);
    } else {
        printf("Acesso negado.\n");
    }
}

// Funções para cadastrar Produto, acesso restrito, apenas admin acessar
void cadastrarProduto (Produto prod[MAX_PROD], int *global_cont) {
    bool sair = false; 
    if (*global_cont > 49) { // Verifica se atingiu o limite de produtos cadastrados
        printf("\nLimite de 50 produtos cadastrados atingido!\n");
        return;
    }

    printf("\nCadastramento de Produto %d°\n" , *global_cont + 1);

    do {
        char validar[10];
        printf("Digite o código numerico: ");
        scanf(" %s", validar); // Explicação a partir da linha 29 sobre por que optei por usar 'char' em vez de 'int'

        if (validarNumInt(validar)) { // Valida se o código é um número inteiro válido
            limparBuffer(); // Limpa o buffer se a validação falhar
            continue; // Retorna ao início do loop do 'do-while'
        }

        prod[*global_cont].codigo = atoi(validar); // Converte a string validar para inteiro e adicionar o valor na variavel prod[*global_cont].codigo  

        sair = true;
        for (int i = 0; i < *global_cont; i++) {
            if (prod[i].codigo == prod[*global_cont].codigo) { // Verifica se o código já existe
                printf("\nErro! Já existe um produto com este codigo!!\n\n");
                limparBuffer();
                sair = false; // Se o código já existir, define sair como falso
                break; // Sai do loop do 'for'
            }
        }         
    } while (!sair);

    do {
        char validar[MAX];
        printf("Digite o nome: ");
        scanf(" %s", validar);

        sair = true;
        if (validarNome(validar)) { // Valida se o nome tem mais de 1 caractere
            printf("\nErro! Nome inválido. O nome do Produto precisa ter mais de 1 caracteres.\n\n");
            limparBuffer();
            sair = false;
            continue; // Retorna ao início do loop do 'do-while'
        }

        strcpy(prod[*global_cont].nome, validar); // Copiar o nome do 'validar' para o 'prod[*global_cont].nome'
        for(int i = 0; i < *global_cont; i++){
            if(strcmp(prod[i].nome, prod[*global_cont].nome) == 0) { // Verifica se o nome já existe
                printf("\nErro! Já existe um produto com este nome!!\n\n");
                limparBuffer();
                sair = false; // Se o nome já existir, define sair como falso
                break; // Sai do loop do 'for'
            }
        } 

    } while (!sair);

    do {
        char validar[12];
        printf("Digite o preço: ");
        scanf(" %s", validar);  // Explicação a partir da linha 29 sobre por que optei por usar 'char' em vez de 'float'

        sair = true;
        if (validarNumFloat(validar)) { // Valida se o preco é um número válido
            limparBuffer();
            sair = false;
            continue; // Retorna ao início do loop do 'do-while'
        }    
        prod[*global_cont].preco = atof(validar); // Converte a string validar para float e adicionar o valor na variavel prod[*global_cont].preco  
    } while (!sair); 

    printf("\nPronto! Produto Cadastrado\n");
    (*global_cont)++; // +1 no contador de produtos cadastrados
}

void removerProduto (Produto prod[MAX_PROD], int *global_cont) {
    bool sair = false;
    int removerprod = 0, posicao = 0, tem = 0;
    
    if (*global_cont <= 0) { // Verifica se existem produtos cadastrados
        printf("\nNenhum produto foi cadastrado.\n");
        return; 
    }  
    
    visualizarListaProduto(prod, global_cont); // Exibe a função visualizarListaProduto, que vai perguntar se o usuário quer ver a lista de produtos

    do {
        char validar[10];
        printf("\nDigite o código do produto que deseja remover: ");
        scanf(" %s", validar); // Explicação a partir da linha 29 sobre por que optei por usar 'char' em vez de 'int'

        sair = true;
        if (validarNumInt(validar)) { // Valida se o código é um número inteiro válido
            limparBuffer();
            sair = false; // Define sair como falso para repetir o loop
        } 
        removerprod = atoi(validar); // Converte a string para inteiro

    } while (!sair);

    for (int i = 0; i < *global_cont; i++) { // Loop para buscar o produto a ser removido
        if (removerprod != prod[i].codigo) {
            tem = 0; // Se o código não for encontrado, continua procurando
        } else { 
            tem = 1; // Se o código for encontrado, define tem como 1
            posicao = i; // Armazena a posição do produto a ser removido
            limparBuffer();
            break; // Sai do loop após encontrar o produto
        }
    }         
    
    if (tem == 1) {
        printf("Produto do codigo %d removido\n", removerprod);
        for (int i = posicao; i < *global_cont; i++) { // Move os produtos seguintes uma posição para trás, preenchendo o espaço do produto que foi removido.
            prod[i].codigo = prod[i+1].codigo;
            strcpy(prod[i].nome, prod[i+1].nome);
            prod[i].preco = prod[i+1].preco;
        }
        (*global_cont)--; // -1 no contador de produtos

        if (*global_cont == 0){
            printf("Último produto cadastrado deletado, agora não tem nennhum produto cadastrado.\n");
        } else {
            printf("\nLista de Produtos Atualizada\n");
        }
    } else {
        printf("\nO código do produto que você quer remover não existe!\n");
    }
    
}

void excluirTodosProdutos (Produto prod[MAX_PROD], int *global_cont) {
    bool sair = false;
    char escolha[3];

    if (*global_cont <= 0) { // Verifica se existem produtos cadastrados
        printf("\nNenhum produto foi cadastrado.\n");
        return; 
    } 
    
    do { // do-while caso o usuário tenha entrada por engano.
        printf("Tem certeza que quer excluir todos os produtos?\n");
        printf("1 - Sim\n2 - Não\n");
        printf("Escolha uma opção: ");
        scanf(" %2s", escolha); // Explicação a partir da linha 2 sobre por que optei por usar 'char' em vez de 'int'.

        if(validarOpcao(escolha)) { // Explicação a partir da linha 10 por que a função validarOpcao é muito importante!
            printf("\nErro! O sistema só aceita o envio de um caractere\nExemplo:\n3 = tem um caractere\n13, 35435, 930200 = tem mais de um caractere\n");
            continue;
        }
        
        switch (escolha[0]) {
        case '1':
            printf("\nTodos os produtos foram excluidos!\n");
            (*global_cont) = 0; // Zera o contador de produtos.
            sair = true; // Define sair como verdadeiro para encerrar o loop.
            break;
        
        case '2':
            printf("\nOk! Nenhum produto foi deletado. Voltando para Área do Administrador\n");
            sair = true;
            break;
        
        default:
            printf("\nErro! Opção inválida.\n");
            limparBuffer();
            break;
        }
    } while (!sair);
}



// Funções para ver lista de produto ou perguntar se quer ver a lista de produto 
void listarProduto (Produto prod[MAX_PROD], const int *global_cont) {
    if (*global_cont <= 0) { // Verifica se a lista de produtos está vazia.
        printf("\nNenhum produto cadastrado.\n");
    } else {
        printf("\n-----------Lista de Produtos-----------\n");
        for (int i = 0; i < *global_cont; i++) { // Exibi todos os produtos cadastrados
            printf("Código: %d\n", prod[i].codigo);
            printf("Nome: %s\n", prod[i].nome);
            printf("Preço: R%.2f\n", prod[i].preco);
            printf("--------------------------------------\n");
        }
    }
}

void visualizarListaProduto (Produto prod[MAX_PROD], const int *global_cont) {
    bool sair = false; // Variável para controlar a saída do loop
    char escolha[3]; // Array para armazenar a escolha do usuário
    do {
        printf("\nDeseja ver a lista de produtos, para ver os codigos dos produtos?\n");
        printf("1 - Sim\n2 - Não\n");
        printf("Escolha uma opção: ");
        scanf(" %2s", escolha); // Explicação a partir da linha 2 sobre por que optei por usar 'char' em vez de 'int'.

        if(validarOpcao(escolha)) { // Explicação a partir da linha 10 por que a função validarOpcao é muito importante!
            printf("\nErro! O sistema só aceita o envio de um caractere\nExemplo:\n3 = tem um caractere\n13, 35435, 930200 = tem mais de um caractere\n");
            continue; // Retorna ao início do loop 'do-while'
        }
        
        switch (escolha[0]) {
        case '1':
            listarProduto(prod, global_cont);
            sair = true;
            break;
        
        case '2':
            sair = true;
            break;
        
        default:
            printf("\nErro! Opção inválida.\n");
            limparBuffer();
            break;
        }
    } while (!sair);
}

void infoProduto (Produto prod[MAX_PROD], const int *global_cont) {
    bool sair = false;
    char escolha[3];
    int codigotem = 0;
    int tem = 0; 
    int posicao = 0;

    if (*global_cont <= 0) { // Verifica se existem produtos cadastrados
        printf("\nNenhum produto foi cadastrado.\n");
        return; 
    } 

    do {
        char validar[10];
        printf("\nDigite o código do produto que deseja ver as informações: ");
        scanf(" %s", validar); // Explicação a partir da linha 29 sobre por que optei por usar 'char' em vez de 'int'

        sair = true;
        if (validarNumInt(validar)) { // Valida se o código é um número inteiro válido
            limparBuffer();
            sair = false; // Define sair como falso para repetir o loop
        } 
        codigotem = atoi(validar); // Converte a string para inteiro

    } while (!sair);

    for (int i = 0; i < *global_cont; i++) { // Loop para buscar o produto a ser removido
        if (codigotem != prod[i].codigo) {
            tem = 0; // Se o código não for encontrado, continua procurando
        } else { 
            tem = 1; // Se o código for encontrado, define tem como 1
            posicao = i; // Armazena a posição do produto a ser removido
            limparBuffer();
            break; // Sai do loop após encontrar o produto
        }
    }

    if (tem) {
        printf("\n-----------Informação do Produto-----------\n");
        printf("Código: %d\n", prod[posicao].codigo);
        printf("Nome: %s\n", prod[posicao].nome);
        printf("Preço: R%.2f\n", prod[posicao].preco);
        printf("--------------------------------------\n");
    } else {
        printf("\nNenhum produto com este código encontrado.\n");
    }
}



// Função do Cliente, acesso livre
void areaCliente (Produto prod[MAX_PROD], int *global_cont, Carrinho car[MAX_PROD], int *global_contcar) {
    bool sair = false;
    char escolha[3];

    do {
        printf("\n\tEntrou na área do Cliente\n");
        printf("1 - Listar Produtos\n2 - Ver informações de um produto\n3 - Adicionar Produto no Carrinho\n4 - Ver o Carrinho\n5 - Ver se tem um produto no carrinho\n6 - Remover do Carrinho\n7 - Finalizar Comprar\n8 - Cancelar Comprar\n9 - Voltar\n");
        printf("Escolha uma opção: ");
        scanf(" %2s", escolha); // Explicação a partir da linha 2 sobre por que optei por usar 'char' em vez de 'int'.

        if(validarOpcao(escolha)) { // Explicação a partir da linha 10 por que a função validarOpcao é muito importante!
            printf("\nErro! O sistema só aceita o envio de um caractere\nExemplo:\n3 = tem um caractere\n13, 35435, 930200 = tem mais de um caractere\n");
            continue;
        }

        switch (escolha[0]) {
        case '1':
            listarProduto(prod, global_cont);
            break;

        case '2':
            infoProduto(prod, global_cont);
            break;

        case '3':
            adicionarCarrinho(prod, global_cont, car, global_contcar);
            break;

        case '4':
            listarCarrinho(prod, global_cont, car, global_contcar);
            break;
        
        case '5':
            temNoCarrinho(car, global_contcar);
            break;

        case '6':
            removerdoCarrinho(prod, global_cont, car, global_contcar);
            break;

        case '7':
            fecharCarrinho(prod, global_cont, car, global_contcar);
            break;

        case '8':
            cancelarCarrinho(global_contcar);
            break;

        case '9':
            sair = true;
            break;
    
        default:
            printf("\nErro! Opção inválida.\n");
            limparBuffer();
            break;
        }

    } while(!sair);
}

void adicionarCarrinho (Produto prod[MAX_PROD], int *global_cont, Carrinho car[MAX_PROD], int *global_contcar) {
    bool sair = false;
    int adicionarmais = 0;
    int posicao = 0;

    if (*global_contcar > 49) { // Verifica se o limite de 50 produtos no carrinho foi atingido.
        printf("\nLimite de 50 produtos no carrinho atingido!\n");
        return;
    }

    if (*global_cont <= 0) { // Verifica se existem produtos cadastrados.
        printf("\nNenhum produto foi cadastrado.\n");
        return; 
    } 
    
    visualizarListaProduto(prod, global_cont); // Exibe a função visualizarListaProduto, que vai perguntar se o usuário quer ver a lista de produtos

    printf("\nAdicionando produto no Carrinho \n");

    do {
        char validar[10];
        printf("\nDigite o código do Produto: ");
        scanf(" %9s", validar);

        if (validarNumInt(validar)) { // Valida se validar é um número inteiro.
            limparBuffer();
            continue;
        }

        int valor = atoi(validar); // Converte string para um inteiro.
        int temcodigo = 0, posicao = 0, temnocar = 0; 

        for (int i = 0; i < *global_cont; i++) { 
            if (prod[i].codigo == valor) { // Verifica se o código do produto existe na lista de produtos.
                temcodigo = 1;
                posicao = i;
                break;
            }
        }

        for (int i = 0; i < *global_contcar; i++) {
            car[*global_contcar].produto = prod[posicao];
            if (car[i].produto.codigo == car[*global_contcar].produto.codigo) { // Verifica se o produto já está no carrinho.
                temnocar = 1;
                adicionarmais = 1;
                posicao = i;
                break;
            }
        }

        sair = false;
        if (temnocar) {
            printf("Este produto já tem no carrinho, por isso você vai adicionar mais quantidade dele!\n");
            sair = true;
        } else if (temcodigo) {
            car[*global_contcar].produto = prod[posicao];
            sair = true;
        } else {
            printf("O código do produto que você quer adicionar não existe!\n");
        }
    } while (!sair);
    
    sair = false;
    do {
        char validar[10];
        printf("Digite a quantidade que você quer comprar deste produto: ");
        scanf(" %9s", validar);

        if (validarNumInt(validar)) { // Valida se validar é um número inteiro
            limparBuffer();
            continue;
        }

        int qtd = atoi(validar); 

        sair = false; 
        if (qtd > 0) { // Verifica se a quantidade é maior que zero.
            if (adicionarmais) {
                    car[posicao].quantidade += qtd;
                    (*global_contcar)--;
            } else {
                car[*global_contcar].quantidade = qtd;
            }
            printf("Produto adicionado no Carrinho!\n");
            sair = true;
        } else {
            printf("\nA quantidade tem que ser maior que 0!\n");
        }

    } while (!sair);
    (*global_contcar)++; // +1 no global_contador do carrinho
}

void removerdoCarrinho (Produto prod[MAX_PROD], int *global_cont, Carrinho car[MAX_PROD], int *global_contcar) {
    bool sair = false;
    int temnocar = 0;
    int posicao = 0;
    int removercar = 0;

    if (*global_contcar <= 0) { // Verifica se existem produtos cadastrados.
        printf("\nNenhum produto foi inserido no Carrinho.\n");
        return; 
    } 
    
    visualizarListaProduto(prod, global_cont); // Exibe a função visualizarListaProduto, que vai perguntar se o usuário quer ver a lista de produtos

    printf("Remover produto do Carrinho \n" );

    do {
        char validar[10];
        printf("\nDigite o código do Produto que deseja remover do carrinho: ");
        scanf(" %9s", validar);

        sair = true;
        if (validarNumInt(validar)) { // Valida se validar é um número inteiro
            limparBuffer();
            sair = false;
            continue;
        } 
        removercar = atoi(validar); // Converte string para um inteiro.

    } while (!sair);
    
    for (int i = 0; i < *global_contcar; i++) {
        if (removercar != car[i].produto.codigo) { // Verifica se tem o produto no carrinho.
            temnocar = 0; // Produto não encontrado.
        } else {
            temnocar = 1; // Produto encontrado.
            posicao = i; // Armazena a posição do produto a ser removido.
            limparBuffer();
            break;
        }
    }

    if (temnocar) {
        printf("Produto removido no Carrinho!\n");
        for (int i = posicao; i < *global_contcar; i++) { // Move os produtos seguintes uma posição para trás, preenchendo o espaço do produto que foi removido.
            car[i].produto = car[i+1].produto;
            car[i].quantidade = car[i+1].quantidade;
        }
        (*global_contcar)--; // -1 no contador do carrinho

        if (*global_contcar == 0){
            printf("Último produto cadastrado deletado, agora não tem nennhum produto cadastrado.\n");
        } else {
            printf("\nCarrinho Atualizado\n");
        }
    } else {
        printf("Não tem este produto no carrinho!\n");
    }
}

void visualizarCarrinho (Produto prod[MAX_PROD], const int *global_cont, Carrinho car[MAX_PROD], int *global_contcar) {
    bool sair = false; // Variável para controlar a saída do loop
    char escolha[3]; // Array para armazenar a escolha do usuário
    do {
        printf("\nDeseja ver o carrinho, para ver os codigos dos produtos?\n");
        printf("1 - Sim\n2 - Não\n");
        printf("Escolha uma opção: ");
        scanf(" %2s", escolha); // Explicação a partir da linha 2 sobre por que optei por usar 'char' em vez de 'int'.

        if(validarOpcao(escolha)) { // Explicação a partir da linha 10 por que a função validarOpcao é muito importante!
            printf("\nErro! O sistema só aceita o envio de um caractere\nExemplo:\n3 = tem um caractere\n13, 35435, 930200 = tem mais de um caractere\n");
            continue;
        }
        
        switch (escolha[0]) {
        case '1':
            listarCarrinho(prod, global_cont, car, global_contcar);
            sair = true;
            break;
        
        case '2':
            sair = true;
            break;
        
        default:
            printf("\nErro! Opção inválida.\n");
            limparBuffer();
            break;
        }
    } while (!sair);
}

void listarCarrinho (Produto prod[MAX_PROD], const int *global_cont, Carrinho car[MAX_PROD], int *global_contcar) {
    if (*global_contcar <= 0) { // Verifica se o carrinho está vazio
        printf("\nNenhum produto foi inserido no Carrinho.\n");
    } else {
        printf("\n-------------------Carrinho-------------------\n");
        for (int i = 0; i < *global_contcar; i++) { // Exibi todos os produtos no carrinho
            printf("#%d Produto do Carrinho\n", i+1);
            printf("    Quantidade deste produto no Carrinho: %d\n", car[i].quantidade);
            printf("    Preço total: R%.2f\n", car[i].quantidade*car[i].produto.preco);
            printf("Informações deste produto:\n");
            printf("    Código: %d\n", car[i].produto.codigo);
            printf("    Nome: %s\n", car[i].produto.nome);
            printf("    Preço: R%.2f\n", car[i].produto.preco);
            printf("----------------------------------------------\n");
        }
    }
}

void cancelarCarrinho (int *global_contcar) {
    bool sair = false;
    char escolha[3];

    if (*global_contcar <= 0) { // Verifica se existem produtos cadastrados
        printf("\nNenhum produto foi inserido no Carrinho para você cancelar a compra.\n");
        return; 
    } 
    
    do {
        printf("Tem certeza que quer cancelar a compra?\n");
        printf("1 - Sim\n2 - Não\n");
        printf("Escolha uma opção: ");
        scanf(" %2s", escolha); // Explicação a partir da linha 2 sobre por que optei por usar 'char' em vez de 'int'.

        if(validarOpcao(escolha)) { // Explicação a partir da linha 10 por que a função validarOpcao é muito importante!
            printf("\nErro! O sistema só aceita o envio de um caractere\nExemplo:\n3 = tem um caractere\n13, 35435, 930200 = tem mais de um caractere\n");
            continue;
        }
        
        switch (escolha[0]) {
        case '1':
            printf("\nCompra cancelada! Carrinho foi esvaziado\n");
            (*global_contcar) = 0; // Zera o contador de produtos.
            sair = true; // Define sair como verdadeiro para encerrar o loop.
            break;
        
        case '2':
            printf("\nOk! Sua compra não vai ser cancelada. Voltando para Área do Cliente\n");
            sair = true;
            break;
        
        default:
            printf("\nErro! Opção inválida.\n");
            limparBuffer();
            break;
        }
    } while (!sair);
}

void fecharCarrinho (Produto prod[MAX_PROD], int *global_cont, Carrinho car[MAX_PROD], int *global_contcar) {
    bool sair = false;
    int finalizar = 0;
    char escolha[3];

    if (*global_contcar <= 0) { // Verifica se existem produtos cadastrados.
        printf("\nNenhum produto foi inserido no Carrinho para você finalizar a compra.\n");
        return; 
    } 
    
    do {
        printf("Tem certeza que quer finalizar a compra?\n");
        printf("1 - Sim\n2 - Não\n");
        printf("Escolha uma opção: ");
        scanf(" %2s", escolha); // Explicação a partir da linha 2 sobre por que optei por usar 'char' em vez de 'int'.

        if(validarOpcao(escolha)) { // Explicação a partir da linha 10 por que a função validarOpcao é muito importante!
            printf("\nErro! O sistema só aceita o envio de um caractere\nExemplo:\n3 = tem um caractere\n13, 35435, 930200 = tem mais de um caractere\n");
            continue;
        }
        
        switch (escolha[0]) {
        case '1':
            finalizar = 1;
            sair = true;
            break;
        
        case '2':
            sair = true;
            break;
        
        default:
            printf("\nErro! Opção inválida.\n");
            limparBuffer();
            break;
        }
    } while (!sair);
    
    if (finalizar) {
        float precofinal = 0;
        int qtd = 0;
        for (int i = 0; i < *global_contcar; i++) {
            precofinal += car[i].quantidade*car[i].produto.preco;
            qtd += car[i].quantidade;
        }

        printf("\nCompra finalizada! Obrigado pela preferência!\n");
        printf("Informações sobre a compra:\n");
        printf("Comprou %d produtos\n", *global_contcar);
        printf("Quantidade comprada %d.\n", qtd);
        printf("Valor total: R$%.2f\n", precofinal);

        printf("\nComo a compra foi finalizada! Carrinho foi esvaziado\n");
        (*global_contcar) = 0;

    } else {
        printf("\nOk! Sua compra não vai ser finalizada. Voltando para Área do Cliente\n");
    }
}

void temNoCarrinho(Carrinho car[MAX_PROD], const int *global_contcar) {
    bool sair = false;
    int codigotem = 0;
    int tem = 0;

    if (* global_contcar<= 0) { // Verifica se existem produtos cadastrados.
        printf("\nNenhum produto foi inserido no Carrinho.\n");
        return; 
    } 

    do {
        char validar[10];
        printf("\nDigite o código do Produto que deseja ver se tem do carrinho: ");
        scanf(" %9s", validar);

        sair = true;
        if (validarNumInt(validar)) { // Valida se validar é um número inteiro
            limparBuffer();
            sair = false;
            continue;
        } 
        codigotem = atoi(validar); // Converte string para um inteiro.

    } while (!sair);

    for (int i = 0; i < *global_contcar; i++) {
        if (codigotem != car[i].produto.codigo) { // Verifica se tem produto no carrinho.
            tem = 0; // Produto não encontrado.
        } else {
            tem = 1; // Produto encontrado.
            limparBuffer();
            break;
        }
    }

    if (tem == 1) {
        printf("\nTem este produto no carrinho\n");
    } else if (tem == 2) {

    } else {
        printf("\nNão tem este produto no carrinho\n");
    }
}
