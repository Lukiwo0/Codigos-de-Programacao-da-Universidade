/*
Exercicio
• Crie um programa que inicialize uma lista encadeada e realize uma busca para verificar se um item x está nesta lista.
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
int buscarLista(Lista *lista, int num);

int main() {
    int valor, anterior, opcao;
    Lista lista;
    int busca;

    criarLista(&lista);

    do {
        printf("\n\t0 - Sair\n\t1 - Inserir Inicio\n\t2 - Inserir Fim\n\t3 - Inserir Meio\n\t4 - Imprimir Listar\n\t5 - Buscar na Listar\n");
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
            printf("Digite a ser buscado: ");
            scanf("%d", &valor);
            busca = buscarLista(&lista, valor);
            if (busca) {
                printf("O Valor %d existe na lista e ele foi encontrado %d vez.\n", valor, busca);
            } else {
                printf("O Valor %d nao existe na lista.\n", valor);
            }
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

int buscarLista(Lista *lista, int num) {
    No *aux = lista->inicio;
    int cont = 0;

    while (aux) {
        if (aux->valor == num) {
            cont++; 
        }
        aux = aux->proximo;
    }


    return cont; 
}