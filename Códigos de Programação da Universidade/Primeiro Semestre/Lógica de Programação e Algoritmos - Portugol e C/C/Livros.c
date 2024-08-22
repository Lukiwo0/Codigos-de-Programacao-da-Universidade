#include <stdio.h>

int main() {
    
    struct Data {
        int dia;
        int mes;
        int ano;
    };

    struct Livro {
        char titulo[100];
        char autor[50];
        struct Data publi;
        char isbn[17];
    };

    struct Livro book[5];

    int i;
    
    printf("Cadastre 5 livros:\n");
    
    for (i = 0; i < 5; i++) {
        printf("Digite o título do livro %d: \n", i + 1);
        scanf("%99s", book[i].titulo);

        printf("Digite o autor do livro %d: \n", i + 1);
        scanf("%49s", book[i].autor);

        printf("Digite a data de publicação do livro %d: \n", i + 1);
        scanf("%d %d %d", &book[i].publi.dia, &book[i].publi.mes, &book[i].publi.ano);

        printf("Digite o ISBN do livro %d: \n", i + 1);
        scanf("%49s", book[i].isbn);
        printf("\n");
    };

    printf("Livros cadastrados\n");
    printf("\n");
    
    for (i = 0; i < 5; i++) {
        printf("Livro %d \n", i + 1);
        printf("Título: %s\n", book[i].titulo);
        printf("Autor: %s\n", book[i].autor);
        printf("Data de publicação: %02d/%02d/%04d\n", book[i].publi.dia, book[i].publi.mes, book[i].publi.ano);
        printf("ISBN: %s\n", book[i].isbn);
        printf("\n");
    };

    return 0;
}