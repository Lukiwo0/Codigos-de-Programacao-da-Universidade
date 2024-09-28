#include <stdio.h>
#include <stdlib.h>  // rand() e srand()
#include <time.h>    // time()

#define TAM 6 // Definindo um tamanho para a array num, se quiser mudar o tamanho dela só mudar o numero

void selectSort (int num[]) {
    int i, j, aux, min;

    for (i = 0; i < TAM-1; i++) { // Percorrer as 5 primeiras posições
        min = i;
        for (j = i+1; j < TAM; j++) { // Em relação a posição i, este for coloca o menor numero nesta posicao i
            if (num[j] < num[min]) {
                min = j; // Min é a posicao com o menor valor 
            }
        }
        aux = num[i]; // Salva o numero da posicao i
        num[i] = num[min]; // Mudando o numero da posicao i para posicao min
        num[min] = aux; // Mudando o numero da posicao min para posicao i

        // Exemplo se o valor de i for 0:
        // Posição: 0 , 1 , 2 , 3, 4 , 5.
        // Numeros: 42, 92, 89, 6, 29, 33.
        // i = 0 
        // min vai ser na posição que tem o menor valor que é = 3 
        // Posição: 0 , 1, 2 , 3 , 4 , 5.
        // Numeros: 6, 92, 89, 42, 29, 33.
        // Faz isso até a penúltima posição 

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

    selectSort(num);

    printf("\n\nOs numeros depois da ordenacao: \n");
    for (int i = 0; i < TAM; i++) {
        printf(" %d,", num[i]);
    }
    
    return 0;
}