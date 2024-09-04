package Atividade01;

import javax.swing.JOptionPane;

public class Questao2 {

	public static void main(String[] args) {

		String valor = JOptionPane.showInputDialog("Digite o valor do raio: ");
		double valornu  = Double.parseDouble(valor);

		double raio = (valornu*valornu) * 3.14;
		String resultado = String.format("%.4f", raio);

		JOptionPane.showMessageDialog(null, resultado);

	}

}
