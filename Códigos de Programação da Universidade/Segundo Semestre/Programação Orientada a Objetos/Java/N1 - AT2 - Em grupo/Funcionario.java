package AtividadeN1;

import java.util.Scanner;

public class Funcionario {
    private String nome;
    private String cargo;
    private float salario;
    private Avaliacao avaliacao;

    Scanner sc = new Scanner(System.in);

    public Funcionario () {
        System.out.println("\n\tDigite os dados do funcionário(a) abaixo\n");
        setNome();
        setCargo();
        setSalario();
        this.avaliacao = new Avaliacao();
    }

    public void details() {
        System.out.println("\t-----------DADOS DO FUNCIONÁRIO(A)-----------");
        System.out.println("Nome: " + getNome());
        System.out.println("Cargo: " + getCargo());
        System.out.println("Salário: " + getSalario());
        System.out.println("\t-----------AVALIAÇÃO FINAL DO FUNCIONÁRIO(A)-----------");
        avaliacao.getNotafinal();
    }
    public String getNome() {
        return nome;
    }

    private void setNome() {
        boolean sair = false;
        do {
            int j = 0;
            System.out.println("Qual o nome do funcionário(a)? ");
            this.nome = sc.nextLine();

            for (int i = 0; i < this.nome.length(); i++) {
                if (Character.isDigit(this.nome.charAt(i))) {
                    j++;
                    break;
                } else {
                    j = 0;
                }
            }

            if (this.nome.length() <= 2) {
                System.out.println("AVISO \nO nome precisa ter mais de 2 caracteres.");
            } else if (j >= 1) {
                System.out.println("AVISO \nO nome não pode ter número.");
            } else {
                sair = true;
            }

        } while (sair != true);
    }

    public String getCargo() {
        return cargo;
    }

    private void setCargo() {
        boolean sair = false;
        do {
            int j = 0;
            System.out.println("Qual o cargo de " + getNome() + "?");
            this.cargo = sc.nextLine();

            for (int i = 0; i < this.cargo.length(); i++) {
                if (Character.isDigit(this.cargo.charAt(i))) {
                    j++;
                    break;
                } else {
                    j = 0;
                }
            }

            if (this.cargo.length() <= 2) {
                System.out.println("AVISO \nO cargo precisa ter mais de 2 caracteres.");
            } else if (j >= 1) {
                System.out.println("AVISO \nO cargo não pode ter número.");
            } else {
                sair = true;
            }

        } while (sair != true);
    }

    public float getSalario() {
        return salario;
    }

    private void setSalario() {
        while (true) {
            System.out.println("Qual o salário de " + getNome() + "?");
            this.salario = sc.nextFloat();
            if (this.salario > 1411.99) {
                break;
            } else {
                System.out.println("AVISO \nO salário não pode ser menor que um salário mínimo (R$ 1.412,00).");
            }
        }
    }

    public void getAvaliacao() {
        avaliacao.getNotas();
        avaliacao.getDatas();
    }

    public void mudarInformacoes() {
        int opc;
        do {
            System.out.println("\nGostaria de mudar qual informação do funcionário?");
            System.out.println("1 - Nome\n2 - Cargo\n3 - Salário\n4 - Tudo");
            opc = sc.nextInt();
            sc.nextLine(); // Limpa o buffer do scanner
            switch (opc) {
                case 1:
                    System.out.println("Digite o novo nome abaixo: ");
                    setNome();
                    break;
                case 2:
                    System.out.println("Digite o novo cargo abaixo: ");
                    setCargo();
                    break;
                case 3:
                    System.out.println("Digite o novo salário abaixo: ");
                    setSalario();
                    break;
                case 4:
                    System.out.println("Digite as novas informações do funcionário abaixo: ");
                    setNome();
                    setCargo();
                    setSalario();
                    this.avaliacao = new Avaliacao();
                    break;
                default:
                    System.out.println("Não foi selecionada uma opção válida!");
                    break;
            }
        } while (opc < 1 || opc > 4);
    }
}
