package AtividadeN1;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int qtd;
        System.out.println("\t\tSEJA BEM-VINDO(A) AO SISTEMA DE AVALIAÇÃO DE FUNCIONÁRIOS V1.0");
        do {
            System.out.println("Quantos funcionários gostaria de cadastrar? ");
            qtd = sc.nextInt();
            if (qtd <= 0) {
                System.out.println("Não se pode cadastrar funcionários menor que 0! Tente novamente!");
            }
        } while (qtd <= 0);

        Funcionario[] funcionarios = new Funcionario[qtd]; //Cria um array de Objetos Funcionários

        for (int i = 0; i < qtd; i++) {
            System.out.println("\nCadastrando o funcionário #" + (i + 1));
            funcionarios[i] = new Funcionario(); // Cadastra um ou mais funcionários
        }

        boolean sair = false;
        do {
            System.out.println("\n\n1 - Mostra as informações do funcionário\n2 - Mudar informações do funcionário" +
                    "\n3 - Mostrar informações de notas e datas\n4 - Sair.");
            System.out.print("Escolha uma opção: ");
            int escolha = sc.nextInt();
            System.out.println();

            switch (escolha) {
                case 1:
                    funcionarios[0].details(); // Mostra as informações do funcionário, bem como sua avaliação
                    break;
                case 2:
                    funcionarios[0].mudarInformacoes(); // Método para mudar informações do funcionário
                    break;
                case 3:
                    funcionarios[0].getAvaliacao(); //Método para mostrar informações de notas e datas
                    break;
                case 4:
                    System.out.println("Saindo do sistema\nTchau! Até a próxima!");
                    sair = true;
                    break;
                default:
                    System.out.println("Número inválido");
                    break;
            }
        } while (sair != true);

    }
}
