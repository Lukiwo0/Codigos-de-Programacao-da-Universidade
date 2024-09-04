package Atividade01;

import java.util.Scanner;

public class Questao5 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Nome do produto: ");
        String produto = sc.next();

        System.out.println("Valor deste produto: ");
        double preco = sc.nextDouble();

        System.out.println("Quanto você quer aplicar de desconto: ");
        double desconto = sc.nextDouble();

        double resultado = preco - (preco * (desconto/100)) ;

        System.out.printf("Produto: %s\nValor sem desconto: R$ %.3f\nValor com desconto:  R$  %.3f", produto, preco, resultado);

    }

}
