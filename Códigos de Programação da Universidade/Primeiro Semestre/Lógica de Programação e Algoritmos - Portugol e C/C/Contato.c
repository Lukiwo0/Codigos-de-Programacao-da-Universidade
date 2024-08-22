#include <stdio.h>

int main () {
    
    struct Contato {
        char nome [20], email[100];
        int telefone;
    };
    
    struct Contato pessoa[20];

    int i, entrar, sair, limite;

    i = 0; 
    entrar = 0;
    sair = 0;
    limite = 0;
   

    printf("Contato\n11Você pode adicionar até 20 contatos");
    printf("\n");

        printf("Gostaria de adicionar um contato?\nDigite 1 para sim e 2 para não: ");
        scanf("%d", &entrar);

        if (entrar == 1) {
            do {
            limite++;
            printf("Digite o Nome: ");
            scanf("%19s", pessoa[limite].nome);
            
            printf("Digite o Email: ");
            scanf("%99s", pessoa[limite].email);

            printf("Digite o Telefone: ");
            scanf("%d", &pessoa[limite].telefone);

            if (limite < 3) {
                printf("\n");
                printf("Gostaria de adicionar mais um contato?\nDigite 1 para sim e 2 para não: ");
                scanf("%d", &sair);
                printf("\n");    
            } else {
                printf("\n");
                printf("Você atingiu o limite de contatos que pode ter.");
            };

            } while (limite < 3 && sair == 1);
        };
    
    i = 0;

    printf("\n"); 
    printf("Agenda de contato");
    printf("\n");

    for (i = 0; i < limite; i++) {
        printf("Contato - %d\n", i+1);

        printf("Nome: %s\n", pessoa[i].nome);
        printf("Email: %s\n", pessoa[i].email);
        printf("Telefone: %d\n", pessoa[i].telefone);
        printf("\n");
    };

    return 0;
}