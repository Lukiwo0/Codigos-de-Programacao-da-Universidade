package Atividade01;

import  java.util.Scanner;

public class Questao3 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Quanto é o salário do funcionário.");

		System.out.println("Digite quantas horas ele trabalhou:");
		double horas = sc.nextDouble();

		System.out.println("Digite quanto ele recebe por hora:");
		double recebe = sc.nextDouble();

		double resultado = recebe * horas;

		System.out.printf("O salário desse funcionário é: R$ %.2f", resultado);


	}

}
