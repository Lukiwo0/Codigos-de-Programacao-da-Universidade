#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>
#include <windows.h>


typedef struct { 
    int dificuldade; 
    int valor1; 
    int valor2; 
    int operacao; 
    int resultado; 
    int pontuacao;
    int qntfases;
} Calcular; 

void jogar(Calcular *calc, int *opc);
void instrucaoJogo();
void menu(Calcular *calc);
void somar(Calcular *calc, int *opc);
void diminuir(Calcular *calc, int *opc);
void multiplicar(Calcular *calc, int *opc);
void mostrarInfo(Calcular *calc);
bool validarInt(char *validar);

int main () {
    // SetConsoleOutputCP(UTF_8);
    
    Calcular calc;

    calc.dificuldade = 0;
    calc.valor1 = 0; 
    calc.valor2 = 0; 
    calc.operacao = 0; 
    calc.resultado = 0; 
    calc.pontuacao = 0;
    calc.qntfases = 0;
    
    menu(&calc);
    printf("Saindo do programa...");

    return 0;
}

void menu (Calcular *calc) {
    bool sair = false;
    int opc = 2;

    do {
        char validar[3];

        printf("\nOlÃ¡ Jogador, espero que esteja bem!");
        printf("\n\n1 - InstruÃ§Ãµes do Jogo");
        printf("\n2 - Nivel Facil");
        printf("\n3 - Nivel Medio");
        printf("\n4 - Nivel Dificil");
        printf("\n5 - Nivel Insano");
        printf("\n6 - Mostrar Desempenho");
        printf("\n7 - Sair\n");

        printf("\nDigite uma opcao: ");
        scanf("%2s", validar);
        fflush(stdin);

        if (validarInt(validar)) {
            fflush(stdin);
            continue;
        }

        opc = atoi(validar);

        switch (opc) {
        case 1:
            instrucaoJogo();
            break;

        case 2:
            jogar(calc, &opc);
            break;

        case 3:
            jogar(calc, &opc);
            break;

        case 4:
            jogar(calc, &opc);
            break;

        case 5:
            jogar(calc, &opc);
            break;

        case 6:
            mostrarInfo(calc);
            break;

        case 7:
            sair = true;
            break;
        
        default:
            printf("\nOpcao invalida!\n");
            break;
        }

    } while (!sair);

}

void mostrarInfo(Calcular *calc) {
    system("cls");
    printf("\nMostrar seu desempenho\n");
    printf("\nFases feitas: %d", calc->qntfases);
    printf("\nQuantidade de fases certas: %d", calc->pontuacao);
    printf("\nQuantidade de fases erradas: %d", calc->qntfases-calc->pontuacao);
    printf("\n\n");
}

void instrucaoJogo() {
    system("cls");
    printf("\nO sistema deve permitir que o jogador escolha o nÃ­vel de dificuldade, resolva operaÃ§Ãµes matemÃ¡ticas (soma, subtraÃ§Ã£o, multiplicaÃ§Ã£o) e acumule pontos conforme acerta as respostas.");
    printf("\nDificuldades:");
    printf("\nNÃ­vel 1 (FÃ¡cil): Valores entre 0 e 10.");
    printf("\nNÃ­vel 2 (MÃ©dio): Valores entre 0 e 100.");
    printf("\nNÃ­vel 3 (DifÃ­cil): Valores entre 0 e 1000.");
    printf("\nNÃ­vel 4 (Insano): Valores entre 0 e 10000.");
    printf("\nGeraÃ§Ã£o de OperaÃ§Ãµes AleatÃ³rias: O sistema deve gerar aleatoriamente uma operaÃ§Ã£o de soma, subtraÃ§Ã£o ou multiplicaÃ§Ã£o para ser resolvida pelo jogador.");
    printf("\nAvaliaÃ§Ã£o de Respostas: O sistema deve avaliar se a resposta dada pelo jogador estÃ¡ correta e, em caso de acerto, incrementar os pontos.");
    printf("\nPontuaÃ§Ã£o: O jogador acumula pontos a cada resposta correta.");
    printf("\nAVISO: NÃ£o coloque '.' ou ',' na resposta!");
    printf("\n-------------------------------------------------------------------------\n\n");
}

void jogar(Calcular *calc, int *opc) {

    int sair = 0;
    int fase = 0;

    if (*opc == 2) {
        printf("\nFÃ¡cil\n\n");
    } else if (*opc == 3) {
        printf("\nMedio\n\n");
    } else if (*opc == 4) {
        printf("\nDificil\n\n");
    } else {
        printf("\nInsano\n\n");
    }
    printf("\nOlÃ¡ Jogador, espero que esteja bem!");

    do {
        char validar[100];
        int escolher = 0;
        int fase = 0;

        srand(time(0));

        printf("\nDigite quantas fases deseja fazer: ");
        scanf("%99s", validar);
        fflush(stdin);

        if (validarInt(validar)) {
            fflush(stdin);
            continue;
        }

        fase = atoi(validar);
        calc->qntfases += fase;

        for (int i = 0; i < fase; i++ ) {
            escolher = rand() % 3; 

            if (escolher == 0) {
                somar(calc, opc);
            } else if (escolher == 1) {
                diminuir(calc, opc);
            } else {
                multiplicar(calc, opc);
            }
        }
        
        do {
            char validar[3];
            int escolha = 0;

            printf("\nDeseja fazer mais fases?\n1 - Sim\n2 - Nao");
            printf("\nDigite sua opcao: ");
            scanf("%2s", validar);
            fflush(stdin);

            if (validarInt(validar)) {
                fflush(stdin);
                continue;
            }

            escolha = atoi(validar);

            if (escolha != 1 && escolha != 2) {
                printf("\nOpcao invalida!");
                continue;
            }

            sair = escolha;

        } while (sair != 1 && sair != 2);
        
    } while (sair != 2);


}

void somar(Calcular *calc, int *opc) {
    char validar[3];
    bool sair = false;
    int resposta = 0;

    calc->valor1 = (*opc == 2) ? rand() % 11          // 0 a 10
                    : (*opc == 3) ? rand() % 101      // 0 a 100
                    : (*opc == 4) ? rand() % 1001     // 0 a 1000
                    : rand() % 10001;                 // 0 a 10000

    calc->valor2 = (*opc == 2) ? rand() % 11          // 0 a 10
                    : (*opc == 3) ? rand() % 101      // 0 a 100
                    : (*opc == 4) ? rand() % 1001     // 0 a 1000
                    : rand() % 10001;                 // 0 a 10000
    
    calc->resultado = calc->valor1 + calc->valor2;


    do {
        printf("\nQuanto e a soma de %d + %d: ", calc->valor1, calc->valor2);
        scanf("%2s", validar);
        fflush(stdin);

        if (validarInt(validar)) {
            fflush(stdin);
            continue;
        }

        sair = true;
        resposta = atoi(validar);

    } while (!sair);

    if(calc->resultado == resposta) {
        printf("Correto!\n");
        ++calc->pontuacao;
    }else{
        printf("Errado! O resultado Ã©: %d", calc->resultado);
    }

}

void diminuir(Calcular *calc, int *opc) {
    char validar[3];
    bool sair = false;
    int resposta = 0;

    calc->valor1 = (*opc == 2) ? rand() % 11          // 0 a 10
                    : (*opc == 3) ? rand() % 101      // 0 a 100
                    : (*opc == 4) ? rand() % 1001     // 0 a 1000
                    : rand() % 10001;                 // 0 a 10000

    calc->valor2 = (*opc == 2) ? rand() % 11          // 0 a 10
                    : (*opc == 3) ? rand() % 101      // 0 a 100
                    : (*opc == 4) ? rand() % 1001     // 0 a 1000
                    : rand() % 10001;                 // 0 a 10000

    if ((calc->valor1 - calc->valor2) > 0 ){
        calc->resultado = calc->valor1 - calc->valor2;

        do {
            printf("\nQual e o resultado entre %d - %d: ", calc->valor1, calc->valor2);
            scanf("%2s", validar);
            fflush(stdin);

            if (validarInt(validar)) {
                fflush(stdin);
                continue;
            }

            sair = true;
            resposta = atoi(validar);
        } while (!sair);
        
     
        if (calc->resultado == resposta){
            printf("Correto!\n");
            calc->pontuacao++;
        } else {
            printf("Incorreto!\nResposta correta: %d", calc->resultado);
        }
        
    } else {
        calc->resultado = calc->valor2 - calc->valor1;

        do {
            printf("\nQual e o resultado entre %d - %d: ", calc->valor2, calc->valor1);
            scanf("%2s", validar);
            fflush(stdin);

            if (validarInt(validar)) {
                fflush(stdin);
                continue;
            }

            sair = true;
            resposta = atoi(validar);
        } while (!sair);

        if (calc->resultado == resposta){
            printf("Correto!\n");
            calc->pontuacao++;
        } else {
            printf("Incorreto!\nResposta correta: %d", calc->resultado);
        }
    }

}

void multiplicar(Calcular *calc, int *opc) {
    char validar[3];
    bool sair = false;
    int resposta = 0;

    calc->valor1 = (*opc == 2) ? rand() % 11          // 0 a 10
                    : (*opc == 3) ? rand() % 101      // 0 a 100
                    : (*opc == 4) ? rand() % 1001     // 0 a 1000
                    : rand() % 10001;                 // 0 a 10000

    calc->valor2 = (*opc == 2) ? rand() % 11          // 0 a 10
                    : (*opc == 3) ? rand() % 101      // 0 a 100
                    : (*opc == 4) ? rand() % 1001     // 0 a 1000
                    : rand() % 10001;                 // 0 a 10000

    calc->resultado = calc->valor1 * calc->valor2;

    do {
        printf("\n___________________________________________________\n");
        printf("\n| >--MULTIPLICACAO--->\n");
        printf("\n|Quanto e a soma de |%d * %d: ", calc->valor1, calc->valor2);
        scanf("%2s", validar);
        fflush(stdin);

        if (validarInt(validar)) {
            fflush(stdin);
            continue;
        }

        sair = true;
        resposta = atoi(validar);
        
    } while (!sair);

        if(calc->resultado == resposta){
            printf("\n|Correto!! :)\n");
            ++calc->pontuacao;
        }else{
            printf("\n|Errado! O resultado Ã©: %d.\n", calc->resultado);
        }
        printf("\n___________________________________________________");

}

bool validarInt(char *validar) {

    int tam = strlen(validar);

    if (tam == 0) {
        system("cls");
        printf("\n\tOpcao vazia!\n");
        return true;
    }

    for (int i = 0; i < tam; i++) {
        if (!isdigit(validar[i])) {
            system("cls");
            printf("\n\tDigite apenas numero.\n");
            return true;
        }
    }

    return false;

}
