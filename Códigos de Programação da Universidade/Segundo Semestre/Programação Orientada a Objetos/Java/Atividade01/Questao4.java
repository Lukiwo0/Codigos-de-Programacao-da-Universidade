package Atividade01;

import javax.swing.JOptionPane;

public class Questao4 {

    public static void main(String[] args) {

        JOptionPane.showMessageDialog(null, "Vou descubrir sua idade!");
        String nome = JOptionPane.showInputDialog("Digite seu nome: ");
        String nasceu = JOptionPane.showInputDialog("Ano que você nasceu: ");
        String ano = JOptionPane.showInputDialog("Em que ano estamos: ");

        int  nasceures = Integer.parseInt(nasceu);
        int  anores = Integer.parseInt(ano);

        int resultado = anores - nasceures;

        JOptionPane.showMessageDialog(null, "Você se chama " + nome + " e possui " + resultado + " anos de idade!");


    }

}
