package Atividade01;

import javax.swing.JOptionPane;

public class Questao6 {

    public static void main(String[] args) {

        JOptionPane.showMessageDialog(null, "Vamos aplicar um aumento de 15% sobre o salário de um funcionário!");

        String salario = JOptionPane.showInputDialog("Digite o salário do funcionário: ");
        double salarionu = Double.parseDouble(salario);

        double novosalario = salarionu + (salarionu * 0.15);

        String mensagem = String.format("Salário antigo: R$ %.2f\n Novo salário: R$ %.2f", salarionu, novosalario);
        JOptionPane.showMessageDialog(null, mensagem);

    }

}
