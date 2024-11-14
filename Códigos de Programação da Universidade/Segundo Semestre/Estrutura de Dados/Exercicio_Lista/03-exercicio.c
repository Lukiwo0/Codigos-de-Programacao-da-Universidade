/*
Exercicio
• Crie um programa que inicialize uma lista encadeada e realize a destruição desta lista em tempo de execução.
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
void destruirLista (Lista *lista);

int main() {
    int valor, anterior, opcao;
    Lista lista;

    criarLista(&lista);

    do {
        printf("\n\t0 - Sair\n\t1 - Inserir Inicio\n\t2 - Inserir Fim\n\t3 - Inserir Meio\n\t4 - Imprimir Listar\n\t5 - Destruir Listar\n");
        scanf("%d", &opcao);

        switch (opcao) {
        case 1:
            printf("Digite um valor: ");
            scanf("%d", &valor);
            inserirInicio(&lista, valor);
            break;

        case 2:
            printf("Digite um valor: ");
            scanf("%d", &valor);
            inserirFim(&lista, valor);
            break;

        case 3:
            printf("Digite um valor e o valor de refencia: ");
            scanf("%d%d", &valor, &anterior);
            inserirMeio(&lista, valor, anterior);
            break;

        case 4:
            imprimirLista(lista);
            break;

        case 5:
            destruirLista(&lista);
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

void destruirLista (Lista *lista) {
    No *atual = lista->inicio;
    No *proximo;

    while (atual != NULL) {
        proximo = atual->proximo;
        free(atual);
        atual = proximo;
    }

    lista->inicio = NULL;
    lista->tam = 0;
    printf("Lista destruida com sucesso.\n");
    
}