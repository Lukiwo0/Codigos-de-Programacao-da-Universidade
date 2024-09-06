package Atividade02.Exercicio04;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Aluno alu = new Aluno();

        System.out.println("Digite o nome do aluno: ");
        alu.aluno = sc.next();

        do {
            System.out.println("Digite a nota do primeiro trimestre deste aluno:");
            alu.nota01 = sc.nextDouble();

            if (alu.nota01 > 30) {
                System.out.println("AVISO: A nota maxima do primeiro trimestre é 30!!");
            }

        } while (alu.nota01 > 30 );

        do {
            System.out.println("Digite a nota do segundo trimestre deste aluno:");
            alu.nota02 = sc.nextDouble();

            if (alu.nota02 > 35) {
                System.out.println("AVISO: A nota maxima do segundo trimestre é 30!!");
            }

        } while (alu.nota02 > 35 );

        do {
            System.out.println("Digite a nota do terceiro trimestre deste aluno:");
            alu.nota03 = sc.nextDouble();

            if (alu.nota03 > 35) {
                System.out.println("AVISO: A nota maxima do terceiro trimestre é 30!!");
            }

        } while (alu.nota03 > 35 );

        alu.resultadoFinal();

        sc.close();

    }
}
