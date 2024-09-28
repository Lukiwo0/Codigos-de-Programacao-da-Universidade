#include <stdio.h>
#include <stdlib.h>  // rand() e srand()
#include <time.h>    // time()

#define TAM 6 // Definindo um tamanho para a array num, se quiser mudar o tamanho dela só mudar o numero

void shellSort (int num[]) {
    int i, j, value, gap = 1;

    // Calcula o valor inicial de gap baseado na sequência de Hibbard
    while (gap < TAM) { 
        gap = 3*gap+1;
    }

    while (gap > 1) { // Reduz o gap e realiza a ordenação por inserção
        gap /= 3; // Divide o gap por 3 para a próxima iteração
        for (i = gap; i < TAM; i++) {
            value = num[i]; // Salva o numero da posicao i em um int value
            j = i - gap; // Atualiza o valor da posição j

            // Se j for maior ou igual a 0 e o value for menor que o numero anterior entre no while
            while (j >= 0 && value < num[j]) { // Move os valores maiores para frente do value
                num[j+gap] = num[j]; // Atualiza o valor da posição [j+gap]
                j -= gap; // Atualiza o valor da posição j e diminiu em 1
            }

            num[j+gap] = value; // Atualiza o valor da posição [j+gap] para a posição correta

            printf("\nVeja o processo de ordenacao: \n");
            for (int i = 0; i < TAM; i++) {
                printf(" %d,", num[i]);
            }
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

    shellSort(num);

    printf("\n\nOs numeros depois da ordenacao: \n");
    for (int i = 0; i < TAM; i++) {
        printf(" %d,", num[i]);
    }

    return 0;
}