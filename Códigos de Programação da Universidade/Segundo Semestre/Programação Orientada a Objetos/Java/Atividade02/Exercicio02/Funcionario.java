package Atividade02.Exercicio02;

public class Funcionario {

    String nome;
    double salario;
    double novosalario;

    public void exibirInfo() {
        System.out.println("Nome do funcionario: " + nome);
        System.out.println("Salario Antigo do funcionario: " + salario);
    }

    public void aumentarSalario() {

        if (salario < 1001) {
            this.novosalario = this.salario * 1.15;
            System.out.println("Parabens! Voce recebeu um aumento de 15%\nSeu novo salario é R$ " + novosalario);
        } else if (salario < 15001) {
            this.novosalario = this.salario * 1.1;
            System.out.println("Parabens! Voce recebeu um aumento de 10%\nSeu novo salario é R$ " + novosalario);
        } else {
            this.novosalario = this.salario * 1.05;
            System.out.println("Parabens! Voce recebeu um aumento de 5%\nSeu novo salario é R$ " + novosalario);
        }

    }
}
