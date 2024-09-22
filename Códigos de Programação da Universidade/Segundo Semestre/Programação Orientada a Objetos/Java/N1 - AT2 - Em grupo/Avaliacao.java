package AtividadeN1;

import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;

public class Avaliacao {
    private double[] notas = new double[3];
    private double notaFinal = 0;
    private String[] datas = new String[3];


    Scanner sc = new Scanner(System.in);
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Avaliacao() {
        System.out.println("Sistema de Avaliação: \nDurante o ano, você será avaliado em três trimestres, recebendo uma nota de 0 a 10 em cada um. \nAo final, será somado as três notas e divida o resultado por 3 para calcular a nota final.");
        setNotas();
        setDatas(datas);
    }

    private void setNotas() {
        for (int i = 0; i < 3; i++) {
            boolean notaValida = false;

            while (!notaValida) {
                System.out.printf("Digite a nota %dª nota: ", i + 1);
                notas[i] = sc.nextDouble();
                sc.nextLine();
                if (notas[i] < 0 || notas[i] > 10) {
                    System.out.println("Nota inválida! Digite uma nota entre 0 e 10!");
                } else {
                    notaValida = true;
                    notaFinal += notas[i];
                }
            }
        }
        notaFinal = notaFinal / 3;
    }

    public void getNotafinal() {
        if (notaFinal <= 3) {
            System.out.printf("Nota Final: %.2f\nDesempenho final: Ruim!", notaFinal);
        } else if (notaFinal <= 4) {
            System.out.printf("Nota Final: %.2f\nDesempenho final: Regular!", notaFinal);
        } else if (notaFinal <= 7) {
            System.out.printf("Nota Final: %.2f\nDesempenho final: Bom!", notaFinal);
        } else {
            System.out.printf("Nota Final: %.2f\nDesempenho final: Excelente!", notaFinal);
        }
    }

    public void getNotas() {
        int opc;
        do {
            System.out.println("\nQual nota gostaria de ver?\n1 - 1ª Nota\n2 - 2ª Nota\n3 - 3ª Nota");
            System.out.print("Escolha uma opção: ");
            opc = sc.nextInt();
            sc.nextLine(); // Limpa o buffer do scanner
            switch (opc) {
                case 1:
                    System.out.println("Nota 1: " + this.notas[0]);
                    break;
                case 2:
                    System.out.println("Nota 2: " + this.notas[1]);
                    break;
                case 3:
                    System.out.println("Nota 3: " + this.notas[2]);
                    break;
                default:
                    System.out.println("Não foi selecionada uma opção válida!");
                    break;
            }
        } while (opc < 1 || opc > 3);
    }

    public void getDatas() {
        int opc;
        do {
            System.out.println("\nQual data gostaria de ver?\n1 - 1ª Data\n2 - 2ª Data\n3 - 3ª Data");
            System.out.print("Escolha uma opção: ");
            opc = sc.nextInt();
            sc.nextLine(); // Limpa o buffer do scanner
            switch (opc) {
                case 1:
                    System.out.println("Data 1: " + this.datas[0]);
                    break;
                case 2:
                    System.out.println("Data 2: " + this.datas[1]);
                    break;
                case 3:
                    System.out.println("Data 3: " + this.datas[2]);
                    break;
                default:
                    System.out.println("Não foi selecionada uma opção válida!");
                    break;
            }
        } while (opc < 1 || opc > 3);
    }

    public void setDatas(String[] datas) {
        datas[0] = lerData(1, null, null);
        datas[1] = lerData(2, datas[0], null);
        datas[2] = lerData(3, datas[0], datas[1]);
    }

    private String lerData(int index, String dataPassada01, String dataPassada02) {
        //System.out.printf("Digite a data da avaliação do %dº trimestre: ", index);

        while (true) {
            System.out.printf("Digite a data da avaliação do %dº trimestre: ", index);
            String dataEntrada = sc.nextLine();

            try {
                LocalDate dataInserida = LocalDate.parse(dataEntrada, formatador);

                if (dataInserida.isAfter(LocalDate.now())) {
                    System.out.println("Data inválida! A data não pode ser no futuro.");
                }  else if (dataPassada01 != null && dataInserida.isEqual(LocalDate.parse(dataPassada01, formatador)) ||
                        dataPassada01 != null && dataInserida.isBefore(LocalDate.parse(dataPassada01, formatador)) ||
                        dataPassada02 != null && dataInserida.isEqual(LocalDate.parse(dataPassada02, formatador)) ||
                        dataPassada02 != null && dataInserida.isBefore(LocalDate.parse(dataPassada02, formatador))) {
                    System.out.println("Data inválida! A data não pode ser igual as anterior" +
                            "\nE a data do 2º trimestre não pode ser antes da data do 1º trimestre." +
                            "\nE a data do 3º trimestre não pode ser antes da data do 1º e 2º trimestre.!");
                } else {
                    return dataInserida.format(formatador);
                }
            } catch (DateTimeException e) {
                System.out.println("Data inválida! Exemplo de Data: 17/09/2024");
            }

        }
    }
}
