#include <stdio.h>
#include <stdlib.h>  // rand() e srand()
#include <time.h>    // time()

#define TAM 6 // Definindo um tamanho para a array num, se quiser mudar o tamanho dela só mudar o numero

void bubbleSort (int num[]) {
    int i, j, aux;
    for (i = TAM-1; i > 0; i--) { // Percorrer as 5 primeiras posições só que começando pela penúltima posição até a primeira
        
        // Desta forma ele leva o numero de maior valor pra frente
        // Verificando se o numero da atual posição é maior que o da proxima posição
        // E como você já colocou o maior número da ultima posicao o valor de i vai diminiu em 1
        // Por isso, o i tem que começar na penúltima posição e ir até a primeira 
        for (j = 0; j < i; j++) { // Percorrer até o valor de i
            if (num[j] > num[j+1]) { // Se o valor da posição atual for maior que o valor da proxima posição, condição verdadeira
                aux = num[j]; // Salva o numero da posicao j
                num[j] = num[j+1]; // Mudando o numero da posicao j para posicao da proxima posição
                num[j+1] = aux; // Mudando o numero da proxima posição para da posicao j
            }
        }
        printf("\nVeja o processo de ordenacao: \n");
        for (int i = 0; i < TAM; i++) {
            printf(" %d,", num[i]);
        }
    }
}

int main() {
    int num[TAM], cont = 0, sair = 0;

    // Inicializa uma nova semente. Se isso não for feito, sempre serão gerados os mesmos números.
    // Isso acontece porque os números "aleatórios" são gerados usando uma fórmula matemática, 
    // portanto, sem a semente, a sequência será a mesma em cada execução.
    // A função time(0) fornece um valor baseado no tempo atual, fazendo com que a semente 
    // seja diferente a cada execução e, assim, gerando uma nova sequência de números aleatórios.
    srand(time(0));

    // Gerando 6 número aleatórios diferentes entre 0 e 99
    for (int i = 0; i < TAM; i++) {
        do { 
            sair = 1;
            // O rand() é um número aleatório que vai ser gerado entre 0 e 32767 e o "% 100", mantem o valor entre 0 e 99
            num[i] = rand() % 100;

            for (int j = 0; j < cont; j++ ) {
                if (num[i] == num[j]) {
                    sair = 0;
                    break;
                }
            }
        } while (!sair);
        cont++;
    }

    printf("Os numeros antes da ordenacao: \n");
    for (int i = 0; i < TAM; i++) {
        printf(" %d,", num[i]);
    }
    printf("\n");

    bubbleSort(num);

    printf("\n\nOs numeros depois da ordenacao: \n");
    for (int i = 0; i < TAM; i++) {
        printf(" %d,", num[i]);
    }

    return 0;
}