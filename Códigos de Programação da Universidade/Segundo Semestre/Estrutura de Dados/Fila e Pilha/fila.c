#include <stdio.h>
#define MAX 5

int fila[MAX], front = -1, rear = -1;

// Função para adicionar um elemento no final da fila
void enqueue(int *valor) {
    if (rear == MAX - 1) {
        printf("\nFila cheia! Não é possível adicionar %d\n", *valor);
    } else {
        if (front == -1) front = 0;
        fila[++rear] = *valor;
        printf("\n%d adicionado à fila.\n", *valor);
    }
}

// Função para remover o elemento do início da fila
int dequeue() {
    if (front == -1 || front > rear) {
        printf("\nFila vazia! Nenhum elemento para remover.\n");
        return -1;
    } else {
        int removerValor = fila[front++];
        printf("\n%d removido da fila.\n", removerValor);
        return removerValor;
    }
}

// Função para mostrar todos os elementos da fila
void mostrarFila() {
    if (front == -1 || front > rear) {
        printf("\nA fila está vazia.\n");
    } else {
        printf("\nElementos na fila: ");
        for (int i = front; i <= rear; i++) {
            printf("%d ", fila[i]);
        }
        printf("\n");
    }
}

int main() {
    int opcao, valor;
    do {
        printf("\nEscolha uma opção:\n1. Inserir na fila\n2. Remover da fila\n3. Mostrar a fila\n4. Sair\n");
        scanf("%d", &opcao);
        switch (opcao) {
            case 1:
                printf("Digite um valor para inserir: ");
                scanf("%d", &valor);
                enqueue(&valor);
                break;
            case 2:
                dequeue();
                break;
            case 3:
                mostrarFila();
                break;
            case 4:
                printf("Encerrando o programa.\n");
                break;
            default:
                printf("Opção inválida. Tente novamente.\n");
        }
    } while (opcao != 4);
    return 0;
}
