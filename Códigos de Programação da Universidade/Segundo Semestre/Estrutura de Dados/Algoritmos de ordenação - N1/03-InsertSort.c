/* Explicação o que é o atual numero e o anterior:
Vamos pegar a lista:
5, 1, 3, 6, 9

Quando o for inicializa a primeira vez, 
o atual numero é 5
e o numero anterior é 1

Por isso o i tem que começar com 1, pra ter um numero anterior
*/

#include <stdio.h>
#include <stdlib.h>  // rand() e srand()
#include <time.h>    // time()

#define TAM 6 // Definindo um tamanho para a array num, se quiser mudar o tamanho dela só mudar o numero

void insertSort (int num[TAM]) {
    int i, j, k;
    for(i = 1; i < TAM; i++) {
        k = num[i]; // Pegando o atual numero 
        j = i - 1; // Pegando o anterior numero 

        // Se j for maior ou igual a 0 e o numero atual for maior que o numero anterior entre no while
        // Por isso o i tem que começar com 1, pra ter um numero anterior
        while(j >= 0 && num[j] > k) {
            num[j+1] = num[j]; // Adicionando o numero do anterior no atual numero
            j--; // -1 no numero
        } // Só vai sair deste while quando não tiver anterior ou o numero atual não for maior que o anterior numero entre no while

        num[j+1] = k; // Adicionando o numero do atual no anterior numero 

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
    
    insertSort(num);

    printf("\n\nOs numeros depois da ordenacao: \n");
    for (int i = 0; i < TAM; i++) {
        printf(" %d,", num[i]);
    }

    return 0;
}