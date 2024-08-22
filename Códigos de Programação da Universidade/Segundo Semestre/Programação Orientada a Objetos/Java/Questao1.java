package atividade;
import java.util.Scanner;

public class QuestaoUm {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite 5 valores e receba sua média aritmética.");
		int n1 = sc.nextInt();
		int n2 = sc.nextInt();
		int n3 = sc.nextInt();
		int n4 = sc.nextInt();
		int n5 = sc.nextInt();
		
		double ma = (n1+n2+n3+n4+n5) / 5.0;
		
		System.out.printf("Média Aritmética: %.2f\n", ma);		
		System.out.println("Valores digitados:" + n1 + ", " + n2 + ", " + n3 + ", " + n4 + ", "+ n5);			

	}

}
