package Atividade02.Exercicio03;

import java.util.concurrent.TransferQueue;

public class Triangulo {

    double a, b, c, p, area;

    public void calcularP() {
        this.p = (this.a + this.b + this.c) / 2;
    }

    public void calcularArea() {
        this.area = Math.sqrt(this.p * (this.p - this.a) * (this.p - this.b) * (this.p - this.c));
    }

    public String trianguloMaior(Triangulo triSegundo) {
        if (this.area > triSegundo.area) {
            System.out.println("\nArea do Primeiro triangulo: " + this.area);
            System.out.println("Area do Segundo triangulo: " + triSegundo.area);
            System.out.println("O Primeiro triangulo é maior que o Segundo." );
        } else if (this.area < triSegundo.area) {
            System.out.println("\nArea do Primeiro triangulo: " + this.area);
            System.out.println("Area do Segundo triangulo: " + triSegundo.area);
            System.out.println("O Segundo triangulo é maior que o Primeiro." );
        } else {
            System.out.println("\nArea do Primeiro triangulo: " + this.area);
            System.out.println("Area do Segundo triangulo: " + triSegundo.area);
            System.out.println("A area dos dois triangulos são iguais." );
        }
        return "acabou";
    }
}
