package Atividade02.Exercicio03;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Triangulo triUm = new Triangulo();
        Triangulo triDois = new Triangulo();

        System.out.println("Primeiro Triangulo");
        System.out.print("Digite um valor para 'a': ");
        triUm.a = sc.nextDouble();
        System.out.print("Digite um valor para 'b': ");
        triUm.b = sc.nextDouble();
        System.out.print("Digite um valor para 'c': ");
        triUm.c = sc.nextDouble();

        triUm.calcularP();
        triUm.calcularArea();

        System.out.println("\nSegundo Triangulo");
        System.out.print("Digite um valor para 'a': ");
        triDois.a = sc.nextDouble();
        System.out.print("Digite um valor para 'b': ");
        triDois.b = sc.nextDouble();
        System.out.print("Digite um valor para 'c': ");
        triDois.c = sc.nextDouble();

        triDois.calcularP();
        triDois.calcularArea();

        String resultado = triUm.trianguloMaior(triDois);
        System.out.println(resultado);


    }
}
