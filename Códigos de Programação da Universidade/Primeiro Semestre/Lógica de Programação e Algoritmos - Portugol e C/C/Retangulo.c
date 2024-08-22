#include <stdio.h>

int main () {

    struct Retangulo {
        float largura, altura; 
    };

    struct Retangulo reta; 

    float area, perimetro;

    printf("Digite o valor da largura: \n");
    scanf("%f", &reta.largura);

    printf("Digite o valor da altura: \n");
    scanf("%f", &reta.altura);

    area = (reta.largura * reta.altura);
    perimetro = (2 * (reta.largura + reta.altura));

    printf("A área desse retângulo é %0.2f\n", area);
    printf("A área desse perímetro é %0.2f", perimetro);
    
    return 0;
}