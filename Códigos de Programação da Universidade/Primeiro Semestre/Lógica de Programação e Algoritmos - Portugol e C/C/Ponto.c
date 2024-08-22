#include <stdio.h>
#include <math.h>

int main () {

    struct Ponto {
        float x, y;
    };

    struct Ponto p1, p2;
    
    float distancia;

    printf("Digite as coordenadas do primeiro ponto x e y: \n");
    scanf("%f %f", &p1.x, &p1.y);
    
    printf("Digite as coordenadas do segundo ponto x e y: \n");
    scanf("%f %f", &p2.x, &p2.y);

    distancia = sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));

    printf("A distância entre os dois pontos é: %f\n", distancia);

    return 0;
    
}