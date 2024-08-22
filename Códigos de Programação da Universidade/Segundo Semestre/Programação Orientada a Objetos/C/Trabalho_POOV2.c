#include <stdio.h>
#include <ctype.h>  // Inclui a fun��o tolower()
#include <string.h> // Inclui a fun��o strcmp()

char analisa_resposta(char []);
float lavar_louca();
float varrer();
float arrumar_casa();
float regar_plantas();

int main () {

    char opc[4];
    float nota = 0.0;
    char tarefas_feitas[4][20];
    char tarefas_nao_feitas[4][20];
    int contador_feitas = 0;
    int contador_nao_feitas = 0;
    int i;

    printf("\t\tTarefas Di�rias de Casa \n\n");
    // D� um espa�o de uma frase pra outra

    printf("Vai Lavar lou�a? (S/N) \n");
    scanf(" %3s", opc); // Limitar a leitura para evitar overflow
    printf("\n"); 
    opc[0] = tolower(opc[0]); // Converte a primeira letra da string para min�scula   
    
    if (analisa_resposta(opc) == 's') { // Verificar se o usu�rio ir� realizar a tarefa 
        nota += lavar_louca();
        if (contador_feitas < 4) {
            strcpy(tarefas_feitas[contador_feitas++], "Lavar lou�a");
        }
    } else {
        if (contador_nao_feitas < 4) {
            strcpy(tarefas_nao_feitas[contador_nao_feitas++], "Lavar lou�a");
        }
    }

    // Agora vai repetir a mesma coisa só que pra outras tarefas
    printf("Vai Varrer a casa? (S/N) \n");
    scanf(" %3s", opc);
    printf("\n");

    if (analisa_resposta(opc) == 's') {
        nota += varrer();
        if (contador_feitas < 4) {
            strcpy(tarefas_feitas[contador_feitas++], "Varrer a casa");
        }
    } else {
        if (contador_nao_feitas < 4) {
            strcpy(tarefas_nao_feitas[contador_nao_feitas++], "Varrer a casa");
        }
    }

    printf("Vai Arrumar a cama? (S/N) \n");
    scanf(" %3s", opc);
    printf("\n");

    if (analisa_resposta(opc) == 's') {
        nota += arrumar_casa();
        if (contador_feitas < 4) {
            strcpy(tarefas_feitas[contador_feitas++], "Arrumar a cama");
        }
    } else {
        if (contador_nao_feitas < 4) {
            strcpy(tarefas_nao_feitas[contador_nao_feitas++], "Arrumar a cama");
        }
    }
    printf("\n");

    printf("Vai Cuidar das plantas? (S/N) \n");
    scanf(" %3s", opc);
    printf("\n");

    if (analisa_resposta(opc) == 's') {
        nota += regar_plantas();
        if (contador_feitas < 4) {
            strcpy(tarefas_feitas[contador_feitas++], "Cuidar das plantas");
    } else {
        if (contador_nao_feitas < 4) {
            strcpy(tarefas_nao_feitas[contador_nao_feitas++], "Cuidar das plantas");
        }
    }
    printf("\n");

    printf("Nota final foi de: %.1f\n", nota);
    
    /* Parte que vai falar oq o usuário fez e o que ele deixou de fazer
    Com sua nota, realizar cada tarefa vale 2.5 pontos. 
    Ex: Se fez tudo nota 10, 
    Se faltou uma coisa pra fazer sua note é 7.5...
    */

    printf("\nTarefas feitas:\n");
    for(i = 0; i < contador_feitas; i++) {
        printf("- %s\n", tarefas_feitas[i]);
    }

    printf("\nTarefas n�o feitas:\n");
    for(i = 0; i < contador_nao_feitas; i++) {
        printf("- %s\n", tarefas_nao_feitas[i]);
    }

    return 0;
}

float lavar_louca () {
    char aux;
    do 
    {
        printf("Opa! Quando voc� terminar, digite um \"S\" \n");
        scanf(" %c", &aux);
        aux = tolower(aux);
    } while (aux != 's');
    
    return 2.5;
}

float varrer () {
    char aux;
    do 
    {
        printf("Opa! Quando voc� terminar, digite um \"S\" \n");
        scanf(" %c", &aux);
        aux = tolower(aux);
    } while (aux != 's');

    return 2.5;
}

float arrumar_casa () {
    char aux;
    do
    {
        printf("Opa! Quando voc� terminar, digite um \"S\" \n");
        scanf(" %c", &aux);
        aux = tolower(aux);
    } while (aux != 's');
    
    return 2.5;
}

float regar_plantas() {
    char aux;
    do
    {
        printf("Opa! Quando voc� terminar, digite um \"S\" \n");
        scanf(" %c", &aux);
        aux = tolower(aux);
    } while (aux != 's');
    
    return 2.5;
}

char analisa_resposta(char opc[]) {
    if (strcmp(opc, "s") == 0 || strcmp(opc, "sim") == 0) {
        return 's';
    } else {
        return 'n';
    }
}
