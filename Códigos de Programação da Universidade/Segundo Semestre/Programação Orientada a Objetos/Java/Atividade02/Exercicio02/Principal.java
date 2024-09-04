package Atividade02.Exercicio02;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Funcionario fun = new Funcionario();

        System.out.println("Digite o nome do funcionario:");
        fun.nome = sc.next();
        System.out.println("Digite o salario do funcionario:");
        fun.salario = sc.nextDouble();
        System.out.println("\n");

        fun.exibirInfo();
        System.out.println("\n");
        fun.aumentarSalario();

        sc.close();
    }
}
