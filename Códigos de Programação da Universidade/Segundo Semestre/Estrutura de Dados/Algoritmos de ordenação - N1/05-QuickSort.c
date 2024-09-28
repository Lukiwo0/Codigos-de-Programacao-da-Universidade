#include <stdio.h>
#include <stdlib.h>  // rand() e srand()
#include <time.h>    // time()

#define TAM 6 // Definindo um tamanho para a array num, se quiser mudar o tamanho dela só mudar o numero

int partindo(int num[], int esquerda, int direita) {
    int pivo = num[direita]; // int usado para dividir a array
    int i = (esquerda - 1); // int para pegar a primeira posicao

    for (int j = esquerda; j < direita; j++) {
        if (num[j] <= pivo) {
            i++;
            int troca = num[i]; // Salva o numero da posicao i
            num[i] = num[j]; // Mudando o numero da posicao i para posicao atual
            num[j] = troca; // Mudando o numero da posicao atual para posicao i
        }
    }
    
    // Colocando o pivo na posição correta
    int troca = num[i+1];
    num[i+1] = num[direita];
    num[direita] = troca;

    return (i+1);
}

void quickSort (int num[], int esquerda, int direita) {
    if (esquerda < direita) {
        // Partindo o array em 2
        int partir = partindo(num, esquerda, direita);

        printf("\nVeja o processo de ordenacao: \n");
        for (int i = 0; i < TAM; i++) {
            printf(" %d,", num[i]);
        }

        quickSort(num, esquerda, partir-1); // ordena a parte esquerda
        quickSort(num, partir+1, direita); // ordena a parte direita
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

    quickSort(num, 0, TAM-1);

    printf("\n\nOs numeros depois da ordenacao: \n");
    for (int i = 0; i < TAM; i++) {
        printf(" %d,", num[i]);
    }

    return 0;
}