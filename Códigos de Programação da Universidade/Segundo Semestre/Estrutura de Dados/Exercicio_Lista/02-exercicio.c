/*
Exercicio
• Crie um programa que inicialize duas listas encadeadas e realize a concatenação destas listas.
*/


#include <stdio.h>
#include <stdlib.h>

typedef struct no {
    int valor;
    struct no *proximo;
} No;

typedef struct {
    No *inicio;
    int tam;
}Lista;

void criarLista (Lista *lista);
void inserirInicio (Lista *lista, int num);
void inserirFim (Lista *lista, int num);
void inserirMeio (Lista *lista, int num, int anterior);
void imprimirLista (Lista lista);
void concatenarLista (Lista *lista1, Lista *lista2);

int main() {
    int valor, anterior, opcao;
    Lista lista1, lista2;

    criarLista(&lista1);
    criarLista(&lista2);

    do {
        printf("\n\t0 - Sair\n\n\tLista 01:\n\t1 - Inserir Inicio\n\t2 - Inserir Fim\n\t3 - Inserir Meio\n\t4 - Imprimir Listar\n");
        printf("\n\tLista 02:\n\t5 - Inserir Inicio\n\t6 - Inserir Fim\n\t7 - Inserir Meio\n\t8 - Imprimir Listar\n");
        printf("\n\t9 - Concatenar Lista 1 e 2\n");

        printf("\nEscolha uma opcao: ");
        scanf("%d", &opcao);

        switch (opcao) {
        case 1:
            printf("Digite um valor: ");
            scanf("%d", &valor);
            inserirInicio(&lista1, valor);
            break;

        case 2:
            printf("Digite um valor: ");
            scanf("%d", &valor);
            inserirFim(&lista1, valor);
            break;

        case 3:
            printf("Digite um valor e o valor de refencia: ");
            scanf("%d%d", &valor, &anterior);
            inserirMeio(&lista1, valor, anterior);
            break;

        case 4:
            imprimirLista(lista1);
            break;

        case 5:
        printf("Digite um valor: ");
        scanf("%d", &valor);
        inserirInicio(&lista2, valor);
        break;

        case 6:
            printf("Digite um valor: ");
            scanf("%d", &valor);
            inserirFim(&lista2, valor);
            break;

        case 7:
            printf("Digite um valor e o valor de refencia: ");
            scanf("%d%d", &valor, &anterior);
            inserirMeio(&lista2, valor, anterior);
            break;

        case 8:
            imprimirLista(lista2);
            break;

        case 9:
            concatenarLista(&lista1, &lista2);
            break;

        default:
            if(opcao != 0) {
                printf("Opcao invalida!\n");
            }
            break;
        }

    } while (opcao != 0);

    printf("Saindo do programa...");
    
    return 0;
}

void criarLista (Lista *lista) {
    lista->inicio = NULL;
    lista->tam = 0;
}

void inserirInicio (Lista *lista, int num) {
    No *novo = malloc(sizeof(No));
    
    if (novo) {
        novo->valor = num;
        novo->proximo = lista->inicio;
        lista->inicio = novo;
        lista->tam++;
    } else {
        printf("Erro ao alocar memoria!\n");
    }
}

void inserirFim (Lista *lista, int num) {
    No *aux, *novo = malloc(sizeof(No));

    if (novo) {
        novo->valor = num;
        novo->proximo = NULL;

        // é o primeiro?
        if (lista->inicio == NULL) {
            lista->inicio = novo;

        } else {
            aux = lista->inicio;
            while (aux->proximo) {
                aux = aux->proximo;
            }
            aux->proximo = novo;
        }
        lista->tam++;

    } else {
        printf("Erro ao alocar memoria!\n");
    }
}

void inserirMeio (Lista *lista, int num, int anterior) {
    No *aux, *novo = malloc(sizeof(No));

    if (novo) {
        novo ->valor = num;

        // é o primeiro?
        if (lista->inicio == NULL) {
            novo->proximo = NULL;
            lista->inicio = novo;
        } else {
            aux = lista->inicio;
            while (aux->valor != anterior && aux->proximo) {
                aux = aux->proximo;
            }
            novo->proximo = aux->proximo;
            aux->proximo = novo;
        }
        lista->tam++;

    } else {
        printf("Erro ao alocar memoria!\n");
    }
}

void imprimirLista (Lista lista) {
    No *no = lista.inicio;
    printf("\n\tLista / tamanho %d: ", lista.tam);

    while (no) {
        printf("%d ", no->valor);
        no = no->proximo;
    }

    printf("\n\n");
}

void concatenarLista (Lista *lista1, Lista *lista2) {
    if (lista1->inicio == NULL) {
        lista1->inicio = lista2->inicio;
    } else {
        No *aux = lista1->inicio;

        while (aux->proximo != NULL) {
            aux = aux->proximo;
        }
        aux->proximo = lista2->inicio;
        
    }
    lista1->tam += lista2->tam;

    lista2->inicio = NULL;
    lista2->tam = 0;

    printf("\n\tLista 2 concatenada na Lista 1 com sucesso!\n");
}