#include <stdio.h>
#include <stdlib.h>  // rand() e srand()
#include <time.h>    // time()

#define TAM 6 // Definindo um tamanho para a array num, se quiser mudar o tamanho dela só mudar o numero

void merge(int num[], int esquerda, int meio, int direita) { // Função para mesclar a parte esquerda e direita
    int i, j, k;
    int tamesquerda = meio - esquerda + 1; // tamanho da sub-array esquerda
    int tamdireita = direita - meio; // tamanho da sub-array direita

    int te[tamesquerda], td[tamdireita]; // arrays temporárias 

    // copiando os numeros para as arrays temporárias 
    for (i = 0; i < tamesquerda; i++) {
        te[i] = num[esquerda + i];
    }
    for (j = 0; j < tamdireita; j++) {
        td[j] = num[meio + 1 + j];
    }
    
    // Mesclando as arrays temporárias no num[]
    i = 0; j = 0; k = esquerda;
    while (i < tamesquerda && j < tamdireita) {
        if (te[i] <= td[j]) {
            num[k] = te[i];
            i++;
        } else {
            num[k] = td[j];
            j++;
        }
        k++;

        printf("\nVeja o processo de ordenacao: \n");
        for (int i = 0; i < TAM; i++) {
            printf(" %d,", num[i]);
        }
    }
    
    while (i < tamesquerda) { // Se houver, copiando os numeros restantes do te[]
        num[k] = te[i];
        i++;
        k++;
    }
    
    while (i < tamdireita) { // Se houver, copiando os numeros restantes do td[]
        num[k] = td[j];
        j++;
        k++;
    }

}

void mergeSort (int num[], int esquerda, int direita) {
    if (esquerda < direita) {
        int meio = esquerda + (direita - esquerda) / 2; // encontrar o meio

        mergeSort(num, esquerda, meio); //ordena a parte esquerda
        mergeSort(num, meio + 1, direita); // ordena a parte direita
        merge(num, esquerda, meio, direita); //Junta as duas partes
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

    mergeSort(num, 0, TAM-1);

    printf("\n\nOs numeros depois da ordenacao: \n");
    for (int i = 0; i < TAM; i++) {
        printf(" %d,", num[i]);
    }

    return 0;
}