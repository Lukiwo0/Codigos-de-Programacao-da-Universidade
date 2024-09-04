package Atividade02.Exercicio01;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Produto prod = new Produto();

        System.out.println("Digite o nome do produto:");
        prod.nomeproduto = sc.next();
        System.out.println("Digite o preco do produto:");
        prod.precoproduto = sc.nextDouble();
        System.out.println("Digite a quantidade do produto:");
        prod.quantidadeproduto = sc.nextInt();
        prod.valortotalproduto = prod.precoproduto * prod.quantidadeproduto;

        prod.exibirInfo();

        System.out.printf("Deseja adicionar mais %s no estoque?\n1 - Sim\n2 - Nao\n", prod.nomeproduto);
        int op = sc.nextInt();

        if (op == 1) {
            System.out.println("Digite a quantidade:");
            int addInt = sc.nextInt();
            prod.adiconarProduto(addInt);
            prod.exibirInfo();
        }

        System.out.printf("Deseja remover %s do estoque?\n1 - Sim\n2 - Nao\n", prod.nomeproduto);
        op = sc.nextInt();

        if (op == 1) {
            System.out.println("Digite uma quantidade para remover:");
            int addInt = sc.nextInt();

            while (addInt > prod.quantidadeproduto) {
                System.out.printf("Voce quer remover mais produtos que tem!!\nQuantidade atual no estoque: %d\n", prod.quantidadeproduto);

                System.out.println("Digite uma quantidade para remover:");
                addInt = sc.nextInt();
            }

            prod.removerProduto(addInt);
            prod.exibirInfo();
        }

        sc.close();
    }
}
