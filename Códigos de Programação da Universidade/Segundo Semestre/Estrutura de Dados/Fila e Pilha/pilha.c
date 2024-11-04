#include <stdio.h>
#define MAX 5

int pilha[MAX], top = -1;

// Função para adicionar um elemento ao topo da pilha
void push(int *valor) {
    if (top == MAX - 1) {
        printf("\nPilha cheia! Não é possível adicionar %d\n", *valor);
    } else {
        pilha[++top] = *valor;
        printf("\n%d adicionado ao topo da pilha.\n", *valor);
    }
}

// Função para remover o elemento do topo da pilha
int pop() {
    if (top == -1) {
        printf("\nPilha vazia! Nenhum elemento para remover.\n");
        return -1;
    } else {
        int removerValor = pilha[top--];
        printf("\n%d removido do topo da pilha.\n", removerValor);
        return removerValor;
    }
}

// Função para mostrar todos os elementos da pilha
void mostrarPilha() {
    if (top == -1) {
        printf("\nA pilha está vazia.\n");
    } else {
        printf("\nElementos na pilha: ");
        for (int i = 0; i <= top; i++) {
            printf("%d ", pilha[i]);
        }
        printf("\n");
    }
}

int main() {
    int opcao, valor;
    do {
        printf("\nEscolha uma opção:\n1. Inserir na pilha\n2. Remover da pilha\n3. Mostrar a pilha\n4. Sair\n");
        scanf("%d", &opcao);
        switch (opcao) {
            case 1:
                printf("Digite um valor para inserir: ");
                scanf("%d", &valor);
                push(&valor);
                break;
            case 2:
                pop();
                break;
            case 3:
                mostrarPilha();
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
