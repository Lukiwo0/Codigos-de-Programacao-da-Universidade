#include <stdio.h>

int main () {

    struct Funcionario {
        char nome[50], cargo[50];
        int idade;
        float salario;
    };

    struct Funcionario pessoa[3];

    int i;

    printf("Cadastre 3 funcionários\n");

    for (i = 0; i < 3; i++) {
        printf("Cadastro do Funcionário %d\n", i + 1 );

        printf("Nome:");
        scanf("%s", pessoa[i].nome);

        printf("Idade:");
        scanf("%d", &pessoa[i].idade);

        printf("Salário:");
        scanf("%f", &pessoa[i].salario);
        
        printf("Cargo:");
        scanf("%s", pessoa[i].cargo);
        printf("\n");
    };

    printf("\n");
    printf("Funcionários cadastrados\n");
    printf("\n");

    for (i = 0; i < 3; i++) {
        printf("Funcionário %d\n", i + 1 );

        printf("Nome: %s\n", pessoa[i].nome);
        printf("Idade: %d\n", pessoa[i].idade);
        printf("Salário: %.2f\n", pessoa[i].salario);
        printf("Cargo: %s\n", pessoa[i].cargo);
        printf("\n");
    };

    return 0;
}