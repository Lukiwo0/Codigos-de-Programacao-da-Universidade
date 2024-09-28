#include <stdio.h>
#include <stdlib.h>  // rand() e srand()
#include <time.h>    // time()

#define TAM 6 // Definindo um tamanho para a array num, se quiser mudar o tamanho dela só mudar o numero

void construirHeap(int num[], int n, int i) { // Reorganização do heapSort
    // Como esse método de organização é baseado em uma raiz de uma árvore
    // Onde temos a raiz e dessa raiz temos seus filhos
    int maior = i; // Atribuindo o valor da raiz para o int maior
    int esquerda = 2 * i + 1; // Filho a esquerda
    int direita = 2 * i + 2; // Filho a direita

    if (esquerda < n && num[esquerda] > num[maior]) { // Se o filho a esquerda for maior que a raiz
        maior = esquerda;
    }

    if (direita < n && num[direita] > num[maior]) { // Se o filho à direita for maior que o maior até agora
        maior = direita;
    }

    if (maior != i) { // Se o maior não for a raiz
        int temp = num[i]; // Salva o numero da posicao i
        num[i] = num[maior]; // Mudando o numero da posicao i para posicao maior
        num[maior] = temp; // Mudando o numero da posicao maior para posicao i
 
        // criando uma nova subárvore dentro da subárvore
        construirHeap(num, n, maior);
    }
}

void heapSort(int num[]) {
    for (int i = TAM / 2 - 1; i >= 0; i--) { //Reorganizando a array
        construirHeap(num, TAM, i);
    }

    for (int i = TAM - 1; i > 0; i--) {
        // Movendo a posicao atual para o final
        int temp = num[0]; // Salva o numero da posicao 0
        num[0] = num[i]; // Mudando o numero da posicao 0 para posicao i
        num[i] = temp; // Mudando o numero da posicao i para posicao 0

        construirHeap(num, i, 0);

        printf("\nVeja o processo de ordenacao: \n");
        for (int j = 0; j < TAM; j++) {
            printf(" %d,", num[j]);
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

    heapSort(num);

    printf("\n\nOs numeros depois da ordenacao: \n");
    for (int i = 0; i < TAM; i++) {
        printf(" %d,", num[i]);
    }

    return 0;
}